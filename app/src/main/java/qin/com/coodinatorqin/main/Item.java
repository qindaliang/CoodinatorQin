package qin.com.coodinatorqin.main;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import qin.com.coodinatorqin.BR;

/**
 * Create by qindl
 * on 2018/11/9
 */
public class Item extends BaseObservable {
    private String name;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public Item(String name) {
        this.name = name;
    }
}
