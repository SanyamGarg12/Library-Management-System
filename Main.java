// ASSUMPTION : ID of Member is its (index number + 1) in Members named ArrayList in Librarian class!

import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    public String author;
    public String title;
    public int copies;
    public int id;
    public int issued = 0;

    public Book(String author, String title, int x) {
        this.author = author;
        this.title = title;
        this.copies = x;
    }

    public void setAuthor(String s) {
        this.author = s;
    }

    public void setTitle(String s) {
        this.title = s;
    }

    public void setCopies(int s) {
        this.copies = s;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int AvailableCopies() {
        return this.copies - this.issued;
    }

}

class Member {
    public String name;
    public int age;
    public long contact;
    public int limit;
    public long fine = 0;
    public ArrayList<Book> mybooks = new ArrayList<Book>();
    public Date issuetime1; // for book 1
    public Date issuetime2; // for book 2
    public Date returntime1; // for book 1
    public Date returntime2; // for book 2

    public Member(String name, int age, long contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.limit = 0;
    }

    public void MyBooks() {
        for (Book b : mybooks) {
            System.out.printf(
                    "ID : " + b.id + "\n" + "Author: " + b.getAuthor() + "\n" + "Title: " + b.getTitle() + "\n"
                            + "Available Copies: " +
                            b.AvailableCopies() + "\n");
        }
    }

    public int IssueBook(Book b) {
        if (this.limit <= 2 && b.copies - b.issued != 0) {
            this.mybooks.add(b);
            b.issued += 1;
            if (this.limit == 0) {
                issuetime1 = new Date();
            } else if (this.limit == 1) {
                issuetime2 = new Date();
            }
            this.limit += 1;
            return 1;
        } else {
            return -1;
        }
    }

    public Book returnBook(Book b) {
        b.issued -= 1;
        if (this.mybooks.indexOf(b) + 1 == 1) {
            returntime1 = new Date();
            if (this.viewFine() != 0) {
                System.out.println("Book ID: " + b.id + " successfully returned. " + this.viewFine()
                        + " Rupees has been charged for a delay of " + (this.returntime1.getTime()
                                - this.issuetime1.getTime() - 10000) / 1000
                        + "\n");
            } else {
                System.out.println("Book Returned Sucessfully!\n");
            }
        }
        if (this.mybooks.indexOf(b) + 1 == 2) {
            returntime2 = new Date();
            if (this.fine != 0) {
                System.out.println("Book ID: " + b.id + " successfully returned. " + this.viewFine()
                        + " Rupees has been charged for a delay of " + (this.returntime2.getTime()
                                - this.issuetime2.getTime() - 10000) / 1000
                        + "\n");
            } else {
                System.out.println("Book Returned Sucessfully!\n");
            }
        }
        this.fine = 0;
        issuetime1 = null;
        issuetime2 = null;
        this.mybooks.remove(b);
        b.issued -= 1;
        this.limit -= 1;
        return b;
    }

    public void payFine() {
        issuetime1 = new Date();
        issuetime2 = new Date();
        this.fine = 0;
    }

    public long viewFine() {
        returntime1 = new Date();
        returntime2 = new Date();
        if (issuetime1 != null) {
            long t1 = (this.returntime1.getTime() - this.issuetime1.getTime()) / 1000;
            if (t1 - 10 > 0) {
                this.fine = (t1 - 10) * 3;
            }
        }
        if (this.issuetime2 != null) {
            long t2 = (this.returntime2.getTime() - this.issuetime2.getTime()) / 1000;
            if (t2 - 10 > 0) {
                this.fine += (t2 - 10) * 3;
            }
        }
        return this.fine;
    }
}

class Librarian {
    public ArrayList<Member> Members = new ArrayList<Member>();
    public ArrayList<Book> Books = new ArrayList<Book>();

    public void AddMember(Member n) {
        this.Members.add(n);
    }

    public void RemoveMember(Member n) {
        this.Members.remove(n);
    }

    public void AddBook(Book b) {
        this.Books.add(b);
        b.id = Books.size();
    }

    public void RemoveBook(Book b) {
        this.Books.remove(b);
    }

    public void viewBooks() {
        for (Book b : Books) {
            System.out.printf(
                    "ID : " + b.id + "\n" + "Author: " + b.getAuthor() + "\n" + "Title: " + b.getTitle() + "\n"
                            + "Available Copies: " +
                            b.AvailableCopies() + "\n");
        }
    }

    public void viewAvailableBooks() {
        for (Book b : Books) {
            if (b.AvailableCopies() != 0)
                System.out.printf(
                        "ID : " + b.id + "\n" + "Author: " + b.getAuthor() + "\n" + "Title: " + b.getTitle() + "\n"
                                + "Available Copies: " +
                                b.AvailableCopies() + "\n");
        }
    }

    public void viewMembers() {
        int i = 0;
        for (Member m : Members) {
            System.out.printf(++i + ". " + m.name + ", " + m.contact + ", " + m.age + "\n");
        }
    }
}

public class ass {
    public static void main(String[] args) {
        Librarian Sanyam = new Librarian();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("1.Enter as a librarian\n2.Enter as a member\n3.Exit\n");
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
                            System.out.println("Member with id " + Sanyam.Members.size() + " is added succesfully\n");
                        }
                    } else if (k == 2) {
                        System.out.println("Enter Member ID to remove from the below list\n");
                        Sanyam.viewMembers();
                        int index = sc.nextInt();
                        sc.nextLine();
                        if (Sanyam.Members.size() < index) {
                            System.out.println("Such Member doesnt exist...Select query again!\n");
                        } else {
                            int i = 1;
                            for (Member m : Sanyam.Members) {
                                if (i == index) {
                                    if (m.limit != 0) {
                                        System.out.println(
                                                "This member can not be removed as he have some books issued to him\n");
                                    } else {
                                        System.out.println("Member " + m.name + " is removed successfully!\n");
                                        Sanyam.RemoveMember(m);
                                    }
                                    break;
                                }
                                i++;
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
                            System.out.println("Book can not be removed as it is issed to someone!\n");
                        }
                    } else if (k == 5) {
                        int i = 1;
                        for (Member m : Sanyam.Members) {
                            System.out
                                    .println("Member ID : " + (i++) + "\n" + "Books Owned : \n" + m.viewFine() + "\n");
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
                    break;
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
                                if (student.limit == 1 && student.mybooks.indexOf(b) != -1) {
                                    i = -2;
                                    break;
                                }
                                if (student.fine == 0) {
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
                                    "One copy of requeste book is already with member, so we can not issue this book again!\n");
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
