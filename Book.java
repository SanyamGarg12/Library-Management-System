package org.example;

public class Book {
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