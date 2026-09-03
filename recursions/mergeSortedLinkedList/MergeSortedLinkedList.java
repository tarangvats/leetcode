package recursions.mergeSortedLinkedList;

public class MergeSortedLinkedList {
    public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }

}
    public static void main(String[] args) {

        // Create two sorted linked lists
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);

        // Merge the two sorted linked lists
        ListNode mergedList = mergeTwoLists(l1, l2);

        // Print the merged linked list
        printList(mergedList);
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }
    
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Non Recursive Approach

        ListNode mergedList = new ListNode();

        ListNode temp1 = list1;
        ListNode temp2 = list2;
        ListNode temp3 = mergedList;

        int val1 = -101;
        int val2 = -101;

        while(temp1!=null && temp2!=null){
            val1 = temp1.val;
            val2 = temp2.val;
            
            if(val1 < val2)
            {
                temp3.next = new ListNode(val1);
                temp1 = temp1.next;
            }
            else{
                temp3.next = new ListNode(val2);
                temp2 = temp2.next;
            }
            
            temp3 = temp3.next;

        }

        while(temp1!=null)
        {
            
            temp3.next = new ListNode(temp1.val);
            temp1 = temp1.next;
            temp3 = temp3.next;
        }
        while(temp2!=null)
        {
            temp3.next = new ListNode(temp2.val);
            temp2 = temp2.next;
            temp3 = temp3.next;
        }

        return mergedList.next;
    }



    
}
