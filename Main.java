import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        HashTable h = new HashTable(1009);


        while(true){

            System.out.println("1- ADD New Contact");
            System.out.println("2- Delete  Contact");
            System.out.println("3- Search about Contact");
            System.out.println("4- display your Contacts");
            System.out.println("5- Update Your Contact ");
            System.out.println("6- Exit ");
            System.out.println("please select your choice : ");


            int choice=in.nextInt();
            in.nextLine();
            if(choice==6)break;
            switch (choice){
                case 1:
                    System.out.println("Enter Name and PhoneNumber: ");
                    h.AddPhoneNumber(in.nextLine(),in.nextLine());
                    break;
                case 2:
                    System.out.println("Enter Name which you want to delete his contact :");
                    h.DeletePhoneNumber(in.nextLine());
                    break;
                case 3:
                    System.out.println("Enter Name which you want to search about this contact  : ");
                    h.SearchPhoneNumber(in.nextLine());
                    break;
                case 4:
                    h.DisplayContact();
                    break;
                case 5:
                    System.out.println("Enter Name and PhoneNumber: ");
                    h.UpdatePhoneNumber(in.nextLine(),in.nextLine());
                    break;
                default:
                    System.out.println("Valid Choice, please try again!");
            }
        }


    }
}





