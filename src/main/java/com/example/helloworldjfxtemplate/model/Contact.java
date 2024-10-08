package com.example.helloworldjfxtemplate.model;

/**
 *  Model for contact class
 * **/
public class Contact {

    private int contactId;
    private String contactName;
    private String contactEmail;

    /** constructor for Contact.
     *
     * @param contactId contact id
     * @param contactEmail contact email
     * @param contactName contact name
     *
     * **/

    public  Contact (int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }


    /**
     * Gets the contact's ID.
     *
     * @return contactId - the contact's ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contact's ID.
     *
     * @param contactId - the contact's ID to set.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the contact's name.
     *
     * @return contactName - the contact's name.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact's name.
     *
     * @param contactName - the contact's name to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the contact's email.
     *
     * @return contactEmail - the contact's email.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the contact's email.
     *
     * @param contactEmail - the contact's email to set.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Override to display contactName as String
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return (contactName);
    }
}
