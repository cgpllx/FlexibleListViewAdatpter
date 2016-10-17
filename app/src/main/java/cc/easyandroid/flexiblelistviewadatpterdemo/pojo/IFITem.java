package cc.easyandroid.flexiblelistviewadatpterdemo.pojo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import cc.easyandroid.flexiblelistviewadapter.EasyFlexibleListViewAdapter;
import cc.easyandroid.flexiblelistviewadapter.FlexibleListViewHolder;
import cc.easyandroid.flexiblelistviewadapter.IFlexible;
import cc.easyandroid.flexiblelistviewadatpterdemo.R;


public class IFITem extends ItemInfo implements IFlexible<IFITem.ListViewHolder> {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.item;
    }

    @Override
    public ListViewHolder createViewHolder(EasyFlexibleListViewAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ListViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(EasyFlexibleListViewAdapter adapter, ListViewHolder holder, int position, List payloads) {

    }

    public class ListViewHolder extends FlexibleListViewHolder {
        public ListViewHolder(final View header_ad, EasyFlexibleListViewAdapter adapter) {
            super(header_ad, adapter);
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
            Toast.makeText(view.getContext(),"po"+position,Toast.LENGTH_SHORT).show();
        }
    }
}
