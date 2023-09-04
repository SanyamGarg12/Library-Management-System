// ASSUMPTION 1 : ID of Member is its phone number*2 as it is unique!
// ASSUMPTION 2 : ID of all copies of a book is same for them as now one member cant issue the same book twice!!
package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Librarian Sanyam = new Librarian();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Enter as a librarian\n2.Enter as a member\n3.Exit\n");
            int operation = sc.nextInt();
            if (operation == 1) {
                while (true) {
                    System.out.println("1.Register a member\n" + //
                            "2.Remove a member\n" + //
                            "3.Add a book\n" + //
                            "4.Remove a book\n" + //
                            "5.View all members along with their books and fines to be paid\n" + //
                            "6.View all books\n" + //
                            "7.Back\n");
                    int k = sc.nextInt();
                    sc.nextLine();
                    if (k == 1) {
                        System.out.println("Enter name: ");
                        String name = sc.nextLine();
                        System.out.println("Enter Age: ");
                        int age = sc.nextInt();
                        System.out.println("Enter phone number: ");
                        long contact = sc.nextLong();
                        int tester = -1;
                        for (Member m : Sanyam.Members) {
                            if (m.contact == contact) {
                                tester = 1;
                                System.out.println("Phone number already registered!\n");
                                break;
                            }
                        }
                        if (tester == -1) {
                            Member k1 = new Member(name, age, contact);
                            Sanyam.AddMember(k1);
                            System.out.println("Member with id " + k1.id + " is added successfully\n");
                        }
                    } else if (k == 2) {
                        System.out.println("Enter Member ID to remove from the below list\n");
                        Sanyam.viewMembers();
                        int index = sc.nextInt();
                        sc.nextLine();
                        int test = -1;
                        for (Member m : Sanyam.Members) {
                            if (m.id == index) {
                                test = 1;
                                break;
                            }
                        }
                        if (test == -1) {
                            System.out.println("Such Member doesnt exist...Select query again!\n");
                        } else {
                            for (Member m : Sanyam.Members) {
                                if (m.id == index) {
                                    if (m.limit != 0) {
                                        System.out.println(
                                                "This member can not be removed as he have some books issued to him\n");
                                    } else {
                                        System.out.println("Member " + m.name + " is removed successfully!\n");
                                        Sanyam.RemoveMember(m);
                                    }
                                    break;
                                }
                            }
                        }

                    } else if (k == 3) {
                        System.out.println("Book Title : ");
                        String title = sc.nextLine();
                        System.out.println("Author : ");
                        String author = sc.nextLine();
                        System.out.println("Copies : ");
                        int copies = sc.nextInt();
                        Book bk = new Book(author, title, copies);
                        Sanyam.AddBook(bk);
                        System.out.println("Book Added Successfully!\n");
                    } else if (k == 4) {
                        System.out.println("BOOK_ID: ");
                        int d = sc.nextInt();

                        Book dump = Sanyam.Books.get(d - 1);
                        if (dump.issued == 0) {
                            Sanyam.RemoveBook(dump);
                            System.out.println("Book_id: " + dump.id + " removed successfully!\n");
                        } else {
                            System.out.println("Book can not be removed as it is issued to someone!\n");
                        }
                    } else if (k == 5) {
                        for (Member m : Sanyam.Members) {
                            System.out
                                    .println("Member ID : " + m.id + "\n" + "Books Owned : \n" + m.viewFine()
                                            + " rupees\n");
                            m.MyBooks();
                        }
                    } else if (k == 6) {
                        Sanyam.viewBooks();
                    } else if (k == 7) {
                        System.out.println("Back to Home Page");
                        break;
                    } else {
                        System.out.println("Invalid Command!\nTry Again!!");
                    }
                }
            } else if (operation == 2) {
                System.out.println("Name : ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.println("Phone No. : ");
                long Phone = sc.nextLong();
                int test = -1;
                Member student = new Member("-1", -1, -1);
                for (Member x : Sanyam.Members) {
                    if (x.name.equals(name) && x.contact == Phone) {
                        student = x;
                        test = 1;
                    }
                }
                if (test == -1) {
                    System.out
                            .println("Member with Name: " + name + " and Phone No: " + Phone + " doesn't exist.\n");
                    continue;
                }
                System.out.println("Welcome!\n");
                while (true) {
                    System.out.println("1.List Available Books\n" + //
                            "2.List My Books\n" + //
                            "3.Issue book\n" + //
                            "4.Return book\n" + //
                            "5.Pay Fine\n" + //
                            "6.Back\n");
                    int k = sc.nextInt();
                    if (k == 1) {
                        Sanyam.viewAvailableBooks();
                    } else if (k == 2) {
                        student.MyBooks();
                    } else if (k == 3) {
                        System.out.println("Book Id: ");
                        int book_id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Book_name: ");
                        String book_name = sc.nextLine();
                        int i = -1;
                        for (Book b : Sanyam.Books) {
                            if (b.id == book_id && book_name.equals(b.title)) {
                                i = 1;
                                if (student.limit == 1 && student.mybooks.contains(b)) {
                                    i = -2;
                                    break;
                                }
                                if (student.viewFine() == 0) {
                                    int res = student.IssueBook(b);
                                    if (res == 1) {
                                        System.out.println("Book issued Successfully!\n");
                                    } else {
                                        System.out.println(
                                                "Either your limit of issuing is reached or enough copies are not available");
                                    }
                                } else {
                                    System.out.println("Pay fine of " + student.viewFine() + " for book ID: "
                                            + student.mybooks.get(0).id + " before issuing another book\n");
                                }
                                break;
                            }
                        }
                        if (i == -1) {
                            System.out.println("Sorry, Book not Found!\n");
                            continue;
                        }
                        if (i == -2) {
                            System.out.println(
                                    "One copy of requested book is already with member, so we can not issue this book again!\n");
                        }
                    } else if (k == 4) {
                        System.out.println("BOOK_ID: ");
                        int x = sc.nextInt();
                        if (Sanyam.Books.size() < x) {
                            System.out.println("No such book issued");
                            continue;
                        }
                        for (Book b : student.mybooks) {
                            if (b.id == x) {
                                student.returnBook(b);
                                break;
                            }
                        }
                    } else if (k == 5) {
                        System.out.println("Your fine of " + student.viewFine() + " is paid successfully!\n");
                        student.payFine();
                    } else if (k == 6) {
                        System.out.println("Back to Home Page");
                        break;
                    } else {
                        System.out.println("Invalid Command!\nTry Again!!");
                    }
                }
            } else if (operation == 3) {
                System.out.println("Thanks for visiting!\n");
                break;
            } else {
                System.out.println("Invalid Operation selected!\nTryAgain");
            }
        }
        sc.close();
    }
}
