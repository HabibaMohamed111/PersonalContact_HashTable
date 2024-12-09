import java.util.ArrayList;
import java.util.Scanner;

public class HashTable {
    int bucket; // number of buckets
    int numOfElements;  //To track the number of elements in HashTable
    HashNode[] table;
    int removeCount;

    // Constructor to initialize bucket count and table
    public HashTable(int bucket) {
        this.bucket = bucket;
        this.numOfElements = 0;
        this.table = new HashNode[bucket];
        this.removeCount=0;

    }

    // class HashNode
    private class HashNode {
        Node node;
        boolean occupiedBefore; // this is flag to check if this node deleted or not
        String name;
        String PhoneNumber;

        // HashNode Constructor
        public HashNode(String name,String PhoneNumber) {
            this.name = name;
            this.PhoneNumber = PhoneNumber;
            this.node = new Node(name,PhoneNumber);
            this.occupiedBefore = true;
        }



        public  class Node {
            String PhoneNumber;
            String name;
            // Node constructor
            public Node(String name,String PhoneNumber) {
                this.name=name;
                this.PhoneNumber = PhoneNumber;
            }
        }
    }








    // Hash function to map values to key
    public static long calc_hash(String key, int table_size) { // key=Ali
        int i, l = key.length(); // l--> numberr of caracters
        long hash = 0;
        for (i = 0; i < l; i++) {
            hash += Character.getNumericValue(key.charAt(i)); // i=0 = A = 65
            hash += (hash << 10); // (2^10 * 65)+65 --->>C
            hash ^= (hash >> 6);//  c+=c/2^6
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        if (hash > 0) return hash % table_size;
        else return -hash % table_size;
    }


    public boolean isEmpty() {
        return numOfElements == 0;
    }

    public boolean isFull() {
        return numOfElements == bucket;
    }

    public int size() {
        return numOfElements;
    }



    // Add function
    public void AddPhoneNumber(String name,String phoneNumber){
        int index=(int)calc_hash(name,bucket);

        if(isFull()){
            System.out.println("Hash Table is FULL");
            return;
        }
        /**
         *
         */
        // Search the entire table to ensure the phone number is unique (O(n) worst case).
        for (int i = 0; i < bucket; i++) {
            if (table[i] != null && table[i].node != null && table[i].node.PhoneNumber.equals(phoneNumber)) {
                System.out.println("This phone number already exists.");
                return;
            }
        }
        HashNode node=new HashNode(name,phoneNumber);
        while (table[index]!=null){
            if(table[index].PhoneNumber != null&&table[index].PhoneNumber.equals(phoneNumber)){
                System.out.println("Contact is already existed ");
                return;
            }
            index=(index+1)%bucket;     // Linear probing
        }
        table[index]=node;
        table[index].name = name;
        numOfElements++;
    }




    // search function
    public boolean SearchPhoneNumber(String key) {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return false;
        }

        int index = (int) calc_hash(key, bucket);
        int originalIndex = index;
        while (table[index] != null && table[index].occupiedBefore) {
            if (table[index].node != null && table[index].name.equals(key)) {
                System.out.println("The phone number of " + key + " is " + table[index].node.PhoneNumber);
                return true;
            }
            index = (index + 1) % bucket;   // linear Probing
            if (index == originalIndex) { // to avoid the infinite loop if the table is full
                break;
            }
        }
        System.out.println("Contact not found: " + key);
        return false;
    }



    // delete function
    public void DeletePhoneNumber(String name) {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        int index = (int) calc_hash(name, bucket);
        int originalIndex = index;

        while (table[index] != null) {
            // Check if this slot matches the name
            if (table[index].node != null && table[index].name.equals(name)) {
                // Delete the contact
                table[index] = null;
                numOfElements--;

                // Rehash subsequent elements to fix the chain
                rehashAfterDeletion(index);

                System.out.println("Deleted contact: " + name + " at index: " + index);
                return;
            }

            // Move to the next index (linear probing)
            index = (index + 1) % bucket;

            // Stop if we've looped back to the starting point
            if (index == originalIndex) break;
        }

        System.out.println("Contact not found: " + name);
    }



    // Helper function to rehash elements after a deletion
    private void rehashAfterDeletion(int startIndex) {
        int index = (startIndex + 1) % bucket;

        while (table[index] != null) {
            // Save the current element
            HashNode temp = table[index];
            table[index] = null; // Remove it from its current position
            numOfElements--;     // Temporarily reduce count to avoid duplication during re-insertion

            // Reinsert the saved element
            AddPhoneNumber(temp.name, temp.node.PhoneNumber);

            // Move to the next index
            index = (index + 1) % bucket;
        }
    }






    // Update function
    public void UpdatePhoneNumber(String name, String newPhoneNumber) {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        int index = (int) calc_hash(name, bucket);
        int originalIndex = index;
        while (table[index] != null && table[index].occupiedBefore) {
            if (table[index].node != null && table[index].name.equals(name)) {
                table[index].node.PhoneNumber = newPhoneNumber; // Update phone number
                System.out.println("Phone number for " + name + " has been updated to " + newPhoneNumber);
                return;
            }
            index = (index + 1) % bucket;
            if (index == originalIndex) {
                break;
            }
        }
        System.out.println("Contact not found: " + name);
    }



    // display function
    public void DisplayContact() {
        if(isEmpty()) System.out.println("The list is empty !");
        else{
            System.out.println("The current Phone numbers in the list are : ");
            for (int i = 0; i < bucket; i++) {
                if (table[i] != null && table[i].node != null) {
                    System.out.println("the index is "+i+" "+"name : "+table[i].node.name + " , The  Phone Number is :" + table[i].node.PhoneNumber);
                }
            }
        }

        System.out.println();
    }



    // Advanced Delete Function
    public void AdvancedDeletePhoneNumber(String name) {
      /*
           دي الميثود اللي انا بشيك فيها لو الليست بتاعتي فاضيه ولا لا
      لو فاضيه هتطبعله وتخرج
      */
        if (isEmpty()) {
            System.out.println("The List Was Founded Empty.");
            return;
        }
    /*
            هنا استخدمت ميثود السيرش بتاعه نورين بشوف هلاقيه ولا لا
            بعد كدا هروح احسب الاندكس بتاعه عن طريق ميثود الهاش كالك
    طيب ليه عملت اتنين مش واحد انا بالطريقه دي بتعامل معالصدمات بحاجه فيما يسمي يعني  اوبن ادريسينج  الاوريجينال اندكس دا بيحافظ علي اول اندكس لقيناه بمعني انه
    لو حصل كولوجن هفضل امشي واعدي عليهم دا بحط فيه قيمه اول حاجه طلعتلي


    */

        if (!SearchPhoneNumber(name)) {
            System.out.println("Contact not found: " + name);
            int index = (int) calc_hash(name, bucket);
            int originalIndex = index;

            while (table[index] != null) {
                if (table[index].node != null) {
                    System.out.println("Deleting contact: " + table[index].name + " with phone number: " + table[index].node.PhoneNumber);
                    table[index] = null;
                    numOfElements--;
                    break;
                }
                index = (index + 1) % bucket;
                if (index == originalIndex) {
                    break;
                }
            }
            return;
        }
    /*
      هنا بقا انا هندلت حاجه هتقابلني الفكره انا المفروض هو انااما اعمل الديليت باي نيم هيمسح اول كونتاكت يقابله في السكه ويخرج ودا مش صح
    افرضي دلوقتي انا عندي اكتر من حد فلنفترض عندي اتنين حبيبه محمد والاتنين اشخاص مختلفه بارقام مختلفه بس ليهم نفس الرقم وانا عاوزه امسح واجده فيهم بس هي متخزنه عندي فجهات الاتصال مش اول اواحده
    انا في الحاله دي لما بمسك تلفني واكتب حبيبه محمد هروح هيطلعي الاسامي كلها اوانا هختار اني امسح انهي واحده فيهم ف دا اللي انا هندلته هنا بقا
    خليته يعرض لليوزر كل الاسامي اللي اسمها موجود بالاسم دا وهو هيختار هو عاوز يحذف انهي واحد
      وعملته باستخدام الاراي ليست اللي خدناها فسنه اولي

    */
        ArrayList<Integer> matchingIndices = new ArrayList<>();
        int index = (int) calc_hash(name, bucket);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index].node != null && table[index].name.equals(name)) {
                matchingIndices.add(index);
            }
            index = (index + 1) % bucket;
            if (index == originalIndex) {
                break;
            }
        }
    /*
    لو كانت القايمه بتاعتي فارغه يعني مفيش اي حاجه بتماتش الكونتاكت دا مفيش زيه يعني هيقولي انها فاضيه ومفيش حاجه بتماتشه وهيحذه ويطلع
    */
        if (matchingIndices.isEmpty()) {
            System.out.println("No contacts found with the name: " + name);
            return;
        }
/*
    هو هنا بقا هيعرضلي كل حاجه ليها علاقه بال كونتاكت دي بمعني هيعرضلي كل الناس اللي ليها نفس الاسم
    وهخيرني منهم هيعرضلي اختار انهي واحد وانا هدخل الرقم اللي هحذفه رقم واحد ولا رقم اتنين ولا رقم تلاته
    */
        System.out.println("Found the following contacts with the name \"" + name + "\":");
        for (int i = 0; i < matchingIndices.size(); i++) {
            int contactIndex = matchingIndices.get(i);
            System.out.println((i + 1) + ". " + table[contactIndex].name + " with phone number: " + table[contactIndex].node.PhoneNumber);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the contact you want to delete: ");
        int choice = scanner.nextInt();
/*
    هنا هندلت حته انو لو مثلا هو مطلعلي تلت اختيارات انا هختار  1/2/3
    افرضي اختارت اربعه او خمسه او دخلت رقم سالب او زيرو
    هيقولي ان دا انفاليد تشويز

    */
        if (choice < 1 || choice > matchingIndices.size()) {
            System.out.println("Invalid choice.");
            return;
        }
    /*
    هنا بقا انا بعمل الاتي انا بدخل الرقم بحذف منه واحد عشان الترتيتبه عندي بتبدا من زيرو
    وهروح احذفه عن طريق اني بخلي قيمته ب نل وهنقص من السايز واحد عشان طرحت واحد
    */
        int selectedIndex = matchingIndices.get(choice - 1);
        System.out.println("Deleting contact: " + table[selectedIndex].name + " with phone number: " + table[selectedIndex].node.PhoneNumber);
        table[selectedIndex] = null;
        numOfElements--;
    /*
     هنا بقا عمليه الريهاشينج دي بعملها عان خاطر الاتي :
      انا لو عندي شويه عناضر وبينها تصادمات المفروض اني كنت بروح احطها يعني فاماكن تانيه وبتاع  هنا هروح ادور لو في عناضر بعد ما المكان دا فضي مان المفروض تتحط فيه ول لا وبعدين اروح اطها
    -- هرجع كل دا مكان ما كان المفروض يتحط بقا عشان لما حصل كولجن شوفتله حته تانيه

    */
        int nextIndex = (selectedIndex + 1) % bucket;
        while (table[nextIndex] != null) {
            HashNode temp = table[nextIndex];
            table[nextIndex] = null;
            numOfElements--;
            AddPhoneNumber(temp.name, temp.node.PhoneNumber);
            nextIndex = (nextIndex + 1) % bucket;
        }
    }











}