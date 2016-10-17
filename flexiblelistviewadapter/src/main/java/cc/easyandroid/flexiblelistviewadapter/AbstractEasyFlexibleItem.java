package cc.easyandroid.flexiblelistviewadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**

 */
public abstract class AbstractEasyFlexibleItem<VH extends EasyFlexibleListViewAdapter.ViewHolder>
        implements IFlexible<VH> {

    private static final String MAPPING_ILLEGAL_STATE = " is not implemented. If you want EasyFlexibleAdapter creates and binds ListViewHolder for you, you must override and implement the method ";

    /* Item flags recognized by the FlexibleAdapter */
    protected boolean mEnabled = true;

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if called but not implemented
     */
    @Override
    public VH createViewHolder(EasyFlexibleListViewAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        throw new IllegalStateException("onCreateViewHolder()" + MAPPING_ILLEGAL_STATE
                + this.getClass().getSimpleName() + ".createViewHolder().");
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if called but not implemented
     */
    @Override
    public void bindViewHolder(EasyFlexibleListViewAdapter adapter, VH holder, int position, List payloads) {
        throw new IllegalStateException("onBindViewHolder()" + MAPPING_ILLEGAL_STATE
                + this.getClass().getSimpleName() + ".bindViewHolder().");
    }

}