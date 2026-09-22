# LRU Cache

## What this code does

This class implements a Least Recently Used (LRU) cache.

An LRU cache keeps the most recently used items at the front and removes the least recently used item when capacity is reached.

It supports:

- `get(key)`
- `put(key, value)`

with the usual behavior:

- if the key exists, return its value
- if not found, return `-1`
- when inserting a new key and the cache is full, evict the least recently used item

---

## Core idea

To make both operations efficient, we use two structures together:

1. `HashMap<Integer, Node>`
   - gives `O(1)` access to any node by key
2. Doubly linked list
   - keeps the order of recency

This combination gives:

- `get(key)`: `O(1)`
- `put(key, value)`: `O(1)`
- `remove least recently used`: `O(1)`

---

## Why a doubly linked list?

Because we need to:

- move a node to the front when it is used
- remove a node quickly when evicting
- maintain order from most recent to least recent

A doubly linked list makes those operations constant time because we can update neighbors directly.

---

## Node structure

```java
private class Node {
    private int key;
    private int value;
    private Node next;
    private Node prev;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}
```

Each node stores:

- the key
- the value
- pointer to previous node
- pointer to next node

---

## Data members

```java
private Map<Integer, Node> map;
private int capacity;
private Node head;
private Node tail;
```

- `map` stores key -> node reference
- `capacity` is the maximum number of entries
- `head` is the most recently used side
- `tail` is the least recently used side

In the constructor:

```java
this.head = new Node(0, 0);
this.tail = new Node(0, 0);
this.head.next = tail;
this.tail.prev = head;
```

This creates a dummy head and tail so we can always safely insert/remove nodes without checking for null.

---

## get(key)

```java
public int get(int key) {
    if (!map.containsKey(key)) {
        return -1;
    }
    Node node = map.get(key);
    deleteNode(node);
    insertAfterHead(node);
    return node.value;
}
```

### What happens here?

- If key is missing, return `-1`
- Otherwise, get the node from the map
- Remove it from its current position
- Insert it right after `head`
- This marks it as the most recently used

---

## put(key, value)

```java
public void put(int key, int value) {
    if (map.containsKey(key)) {
        Node node = map.get(key);
        node.value = value;
        deleteNode(node);
        insertAfterHead(node);
    } else {
        if (map.size() == capacity) {
            Node node = tail.prev;
            map.remove(node.key);
            deleteNode(node);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        insertAfterHead(node);
    }
}
```

### Cases

1. Key already exists
   - update its value
   - move it to the front as most recent

2. Key is new
   - if cache is full, remove the tail.prev node (least recently used)
   - create a new node
   - insert it after `head`

---

## deleteNode(node)

```java
public void deleteNode(Node node) {
    Node prevNode = node.prev;
    Node nextNode = node.next;
    prevNode.next = nextNode;
    nextNode.prev = prevNode;
}
```

This removes a node from the linked list by reconnecting its previous and next neighbors.

---

## insertAfterHead(node)

```java
public void insertAfterHead(Node node) {
    Node currAfterHead = this.head.next;
    this.head.next = node;
    node.prev = this.head;
    node.next = currAfterHead;
    currAfterHead.prev = node;
}
```

This inserts the node right after `head`, making it the newest element.

---

## Example walkthrough

```java
LRUCache cache = new LRUCache(2);
cache.put(1, 1);
cache.put(2, 2);
System.out.println(cache.get(1)); // 1
cache.put(3, 3); // evicts 2
System.out.println(cache.get(2)); // -1
cache.put(4, 4); // evicts 1
System.out.println(cache.get(1)); // -1
System.out.println(cache.get(3)); // 3
System.out.println(cache.get(4)); // 4
```

### Step-by-step

- Start with capacity 2
- Put 1, then 2
- `get(1)` makes 1 most recent
- `put(3)` evicts 2 because it was least recently used
- `get(2)` returns `-1` because 2 was removed
- `put(4)` evicts 1
- `get(1)` returns `-1`
- `get(3)` and `get(4)` return correct values

---

## Time complexity

- `get`: `O(1)`
- `put`: `O(1)`
- `deleteNode`: `O(1)`
- `insertAfterHead`: `O(1)`

Overall:

- Time: `O(1)` per operation
- Space: `O(capacity)`

---

## Revision summary

The key idea is:

- HashMap gives direct access by key
- Doubly linked list maintains eviction order
- Most recent items stay near the front
- Least recent items stay near the tail

This is the standard and optimal approach for LRU implementation.

---

## Important takeaway

If you forget the linked-list ordering, the cache will not know which key to evict correctly. The correctness depends on always:

- removing a used node from the current position
- inserting it at the front after access/update
- evicting `tail.prev` when the cache is full

This is the heart of the LRU design.
