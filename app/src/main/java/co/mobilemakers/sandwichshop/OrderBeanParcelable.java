package co.mobilemakers.sandwichshop;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 27/01/15.
 */
public class OrderBeanParcelable implements Parcelable{

    private String nameSandwich;
    private String toppingsOptions;

    public OrderBeanParcelable(){}

    public OrderBeanParcelable(Parcel in){
        readFromParcel(in);
    }



    public String getNameSandwich() {
        return nameSandwich;
    }

    public void setNameSandwich(String nameSandwich) {
        this.nameSandwich = nameSandwich;
    }

    public String getToppingsOptions() {
        return toppingsOptions;
    }

    public void setToppingsOptions(String toppingsOptions) {
        this.toppingsOptions = toppingsOptions;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameSandwich);
        dest.writeString(toppingsOptions);
    }

    private void readFromParcel(Parcel in){
        nameSandwich = in.readString();
        toppingsOptions = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public OrderBeanParcelable createFromParcel(Parcel in) {
            return new OrderBeanParcelable(in);
        }
        public OrderBeanParcelable[] newArray(int size)
        { return new OrderBeanParcelable[size];
        }
    };
}
