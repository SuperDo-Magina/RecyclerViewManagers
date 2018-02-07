package com.superdo.magina.managers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *
 *      author LYB
 *      time   18/2/5 下午12:01
 *      des    列表项辅助拖拽回调
 *
 * </pre>
 */

public class ItemDragHelperCallback extends ItemTouchHelper.Callback {

    public final static int DIRECTION_ALL = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    public final static int DIRECTION_VERTICAL = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

    private final static String TAG = "Test MyItemTouchCb";
    private RecyclerView.Adapter adapter;
    private List list;
    private int dragFlags = DIRECTION_ALL;

    private OnDragListener dragListener;

    public ItemDragHelperCallback(RecyclerView.Adapter adapter, List list) {
        this.adapter = adapter;
        this.list = list;
    }

    public void setDragFlags(int dragFlags) {
        this.dragFlags = dragFlags;
    }

    public void setDragListener(OnDragListener dragListener) {
        this.dragListener = dragListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        final int swipeFlags = 0;
        return makeMovementFlags(this.dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        adapter.notifyItemMoved(fromPosition, toPosition);
        performDrag( viewHolder,  target);
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        switch (actionState) {
            case ItemTouchHelper.ACTION_STATE_DRAG:
                performDragBegin( viewHolder);
                break;
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        performDragComplete( viewHolder);
    }

    private void performDrag(RecyclerView.ViewHolder fromHolder, RecyclerView.ViewHolder targetHolder) {
        if (dragListener != null) {
            dragListener.onDrag(fromHolder, targetHolder);
        }
    }

    private void performDragComplete(RecyclerView.ViewHolder viewHolder) {
        if (dragListener != null) {
            dragListener.onDragComplete(viewHolder);
        }
    }

    private void performDragBegin(RecyclerView.ViewHolder viewHolder) {
        if (dragListener != null) {
            dragListener.onDragBegin(viewHolder);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

    public interface OnDragListener{

        void onDragBegin(RecyclerView.ViewHolder viewHolder);

        void onDrag(RecyclerView.ViewHolder fromHolder, RecyclerView.ViewHolder targetHolder);

        void onDragComplete(RecyclerView.ViewHolder viewHolder);
    }
}
