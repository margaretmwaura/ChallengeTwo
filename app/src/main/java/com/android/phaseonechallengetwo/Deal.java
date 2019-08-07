package com.android.phaseonechallengetwo;

import android.os.Parcel;
import android.os.Parcelable;

public class Deal implements Parcelable
{
    String area;
    int amount;
    String destination;
    String url;

    public Deal()
    {

    }

    public Deal(String area, int amount, String destination, String url) {
        this.area = area;
        this.amount = amount;
        this.destination = destination;
        this.url = url;
    }

    protected Deal(Parcel in) {
        area = in.readString();
        amount = in.readInt();
        destination = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(area);
        dest.writeInt(amount);
        dest.writeString(destination);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Deal> CREATOR = new Creator<Deal>() {
        @Override
        public Deal createFromParcel(Parcel in) {
            return new Deal(in);
        }

        @Override
        public Deal[] newArray(int size) {
            return new Deal[size];
        }
    };

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
