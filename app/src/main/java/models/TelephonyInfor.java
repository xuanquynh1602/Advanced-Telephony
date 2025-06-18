package models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TelephonyInfor implements Serializable {
    private String displayName;
    private String phoneNumber;

    public TelephonyInfor() {
    }

    public TelephonyInfor(String displayName, String phoneNumber) {
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return this.displayName+"\n"+this.phoneNumber;
    }
}
