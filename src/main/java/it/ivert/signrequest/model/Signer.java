package it.ivert.signrequest.model;

public class Signer {

    /* required
    string <email> (Email) [ 1 .. 255 ] characters */
    private String email;

    /* string (First name) <= 255 characters */
    private String firstName;

    /* string (Last name) <= 255 characters */
    private String lastName;

    /* boolean (Needs to sign)
    Default: true */
    private boolean needsToSign;

    /* boolean (Approve only) */
    private boolean approveOnly;

    /* boolean (Notify only) */
    private boolean notifyOnly;

    /* boolean (In person) */
    private boolean inPerson;

    /* integer (Order) [ 0 .. 2147483647 ] */
    private int order;

    /* string (Language) Nullable
    Enum:"en" "en-gb" "nl" "fr" "de" "he" "da" "fi" "hu" "it" "no" "pl" "pt" "es" "sv" "ru" */
    private String language;

    /* boolean (Force language) */
    private boolean forceLanguage;

    private String verifyPhoneNumber;

    /* string (Verify bank account) <= 255 characters Nullable */
    private String verifyBankAccount;

    /* string (Embed url user id) <= 255 characters Nullable */
    private String embedUrlUserId;

    /* string <uri> (Redirect url) <= 2100 characters Nullable */
    private String redirectUrl;

    /* string <uri> (After document) */
    private String afterDocument;

    /*
    Array of object (InlineDocumentSignerIntegrationData)
    integrations
    */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isNeedsToSign() {
        return needsToSign;
    }

    public void setNeedsToSign(boolean needsToSign) {
        this.needsToSign = needsToSign;
    }

    public boolean isApproveOnly() {
        return approveOnly;
    }

    public void setApproveOnly(boolean approveOnly) {
        this.approveOnly = approveOnly;
    }

    public boolean isNotifyOnly() {
        return notifyOnly;
    }

    public void setNotifyOnly(boolean notifyOnly) {
        this.notifyOnly = notifyOnly;
    }

    public boolean isInPerson() {
        return inPerson;
    }

    public void setInPerson(boolean inPerson) {
        this.inPerson = inPerson;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isForceLanguage() {
        return forceLanguage;
    }

    public void setForceLanguage(boolean forceLanguage) {
        this.forceLanguage = forceLanguage;
    }

    public String getVerifyPhoneNumber() {
        return verifyPhoneNumber;
    }

    public void setVerifyPhoneNumber(String verifyPhoneNumber) {
        this.verifyPhoneNumber = verifyPhoneNumber;
    }

    public String getVerifyBankAccount() {
        return verifyBankAccount;
    }

    public void setVerifyBankAccount(String verifyBankAccount) {
        this.verifyBankAccount = verifyBankAccount;
    }

    public String getEmbedUrlUserId() {
        return embedUrlUserId;
    }

    public void setEmbedUrlUserId(String embedUrlUserId) {
        this.embedUrlUserId = embedUrlUserId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getAfterDocument() {
        return afterDocument;
    }

    public void setAfterDocument(String afterDocument) {
        this.afterDocument = afterDocument;
    }
}

