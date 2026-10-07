#!/usr/bin/env python3
"""Refresh the progress tables in every sheet README. Run from anywhere: python3 "SDE Sheet/progress.py"."""
import pathlib, re

START, END = "<!-- progress:start -->", "<!-- progress:end -->"

def bar(done, total, width=20):
    filled = round(width * done / total) if total else 0
    return "█" * filled + "░" * (width - filled)

for readme in sorted(pathlib.Path(__file__).parent.glob("*/README.md")):
    sections, current = [], None
    for line in readme.read_text().splitlines():
        if line.startswith("## "):
            current = [line[3:].strip(), 0, 0, 0, 0]  # name, total, solved, revised, visited
            sections.append(current)
        elif current and re.match(r"\| \d+ \|", line):
            cells = [c.strip() for c in line.strip().strip("|").split("|")]
            current[1] += 1
            current[2] += cells[-3].lower() == "[x]"
            current[3] += cells[-2].lower() == "[x]"
            current[4] += int(cells[-1]) if cells[-1].isdigit() else 0
    sections = [s for s in sections if s[1]]
    total, solved, revised, visits = (sum(s[i] for s in sections) for i in (1, 2, 3, 4))

    lines = ["## Progress", "",
             f"**Solved: {solved} / {total}** `{bar(solved, total)}` {100 * solved // max(total, 1)}%  ",
             f"**Revised: {revised} / {total}**  ",
             f"**Total visits: {visits}**", "",
             "| Topic | Solved | Revised | Visits |", "|---|---|---|---|"]
    lines += [f"| {n} | {s} / {t} | {r} / {t} | {v} |" for n, t, s, r, v in sections]

    text = readme.read_text()
    block = f"{START}\n" + "\n".join(lines) + f"\n{END}"
    readme.write_text(re.sub(re.escape(START) + r".*?" + re.escape(END), lambda _: block, text, flags=re.S))
    print(f"{readme.parent.name}: {solved}/{total} solved, {revised}/{total} revised")
