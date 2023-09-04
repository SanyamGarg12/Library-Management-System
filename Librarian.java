package org.example;

import java.util.ArrayList;

class Librarian {
    public ArrayList<Member> Members = new ArrayList<>();
    public ArrayList<Book> Books = new ArrayList<>();

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
        for (Member m : Members) {
            System.out.printf(m.id + ". " + m.name + ", " + m.contact + ", " + m.age + "\n");
        }
    }
}
