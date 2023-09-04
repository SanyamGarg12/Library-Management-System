package org.example;

import java.util.Date;
import java.util.ArrayList;

class Member {
    public String name;
    public int age;
    public long contact;
    public int limit;
    public long id;
    public long fine = 0;
    public ArrayList<Book> mybooks = new ArrayList<>();
    public Date issuetime1; // for book 1
    public Date issuetime2; // for book 2
    public Date returntime1; // for book 1
    public Date returntime2; // for book 2

    public Member(String name, int age, long contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.limit = 0;
        this.id = contact * 2;
    }

    public void MyBooks() {
        for (Book b : mybooks) {
            System.out.printf(
                    "ID : " + b.id + "\n" + "Author: " + b.getAuthor() + "\n" + "Title: " + b.getTitle() + "\n"
                            + "\n");
        }
    }

    public int IssueBook(Book b) {
        if (this.limit < 2 && b.copies - b.issued != 0) {
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

    public void returnBook(Book b) {
        if (this.mybooks.indexOf(b) + 1 == 1) {
            returntime1 = new Date();
            if (this.viewFine() != 0) {
                System.out.println("Book ID: " + b.id + " successfully returned. " + this.viewFine()
                        + " Rupees has been charged for a delay of " + (this.returntime1.getTime()
                        - this.issuetime1.getTime() - 10000) / 1000
                        + "\nAlso if fine of other book is also pending they are also paid within this\n");
            } else {
                System.out.println("Book Returned Successfully!\n");
            }
            issuetime1 = null;
        }
        if (this.mybooks.indexOf(b) + 1 == 2) {
            returntime2 = new Date();
            if (this.viewFine() != 0) {
                System.out.println("Book ID: " + b.id + " successfully returned. " + this.viewFine()
                        + " Rupees has been charged for a delay of " + (this.returntime2.getTime()
                        - this.issuetime2.getTime() - 10000) / 1000
                        + "\nAlso if fine of other book is also pending they are also paid within this\n");
            } else {
                System.out.println("Book Returned Successfully!\n");
            }
            issuetime2 = null;
        }
        this.fine = 0;
        this.mybooks.remove(b);
        b.issued -= 1;
        this.limit -= 1;
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
