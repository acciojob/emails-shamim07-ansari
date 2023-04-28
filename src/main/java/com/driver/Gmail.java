package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    List<Mail> Inbox;
    List<Mail> Trash;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        Inbox = new ArrayList<>();
        Trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox.size() == inboxCapacity) {
            Mail mail = Inbox.get(0);
            Trash.add(mail);
            Inbox.remove(0);
        }
        Mail mail = new Mail(date, sender, message);
        Inbox.add(mail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        try {
            for(Mail mail : Inbox) {
                if(mail.getMessage().equals(message)) {
                    Inbox.remove(mail);
                    Trash.add(mail);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.isEmpty()) return null;
        else return Inbox.get(Inbox.size()-1).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.isEmpty()) return null;
        else return Inbox.get(0).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int cnt = 0;
        for(Mail mail : Inbox) {
            if(mail.getDate().compareTo(start) >= 0 && mail.getDate().compareTo(end) <= 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
