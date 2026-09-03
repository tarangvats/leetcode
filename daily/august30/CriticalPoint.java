public class CriticalPoint {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next.next = new ListNode(1);

        CriticalPointSolution solution = new CriticalPointSolution();
        int[] result = solution.nodesBetweenCriticalPoints(head);

        System.out.println("Minimum distance: " + result[0]);
        System.out.println("Maximum distance: " + result[1]);
         
        // Test case with no critical points
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        int[] result2 = solution.nodesBetweenCriticalPoints(head2);
        System.out.println("Minimum distance: " + result2[0]);
        System.out.println("Maximum distance: " + result2[1]);  
        
    }
}
