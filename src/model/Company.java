package model;

public class Company {
    private String id;
    private String name;
    private String contact;
    private String address;

    public Company() {
    }

    public Company(String id, String name, String contact, String address) {
        this.setId(id);
        this.setName(name);
        this.setContact(contact);
        this.setAddress(address);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}