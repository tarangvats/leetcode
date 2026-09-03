import java.util.ArrayList;
import java.util.List;

class CriticalPointSolution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[] { -1, -1 };
        }

        List<Integer> criticalPoints = new ArrayList<>();
        ListNode previous = head;
        ListNode current = head.next;
        int index = 1;

        while (current.next != null) {
            ListNode next = current.next;
            if ((previous.val < current.val && current.val > next.val)
                    || (previous.val > current.val && current.val < next.val)) {
                criticalPoints.add(index);
            }

            previous = current;
            current = next;
            index++;
        }

        if (criticalPoints.size() < 2) {
            return new int[] { -1, -1 };
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < criticalPoints.size(); i++) {
            minDistance = Math.min(minDistance, criticalPoints.get(i) - criticalPoints.get(i - 1));
        }

        int maxDistance = criticalPoints.get(criticalPoints.size() - 1) - criticalPoints.get(0);
        return new int[] { minDistance, maxDistance };
    }
}