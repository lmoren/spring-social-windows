package org.springframework.social.windows.api;

import java.io.Serializable;

/**
 * @author Lukasz Moren
 */
public class Emails implements Serializable {

    private String preferred;

    private String account;

    private String personal;

    private String business;

    public Emails(String preferred, String account, String personal, String business) {
        this.preferred = preferred;
        this.account = account;
        this.personal = personal;
        this.business = business;
    }

    public String getPreferred() {
        return preferred;
    }

    public String getAccount() {
        return account;
    }

    public String getPersonal() {
        return personal;
    }

    public String getBusiness() {
        return business;
    }
}
