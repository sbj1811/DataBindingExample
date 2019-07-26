package com.sjani.databindingexample.MainList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.sjani.databindingexample.Models.Event;
import com.sjani.databindingexample.EventListViewModel;

import java.util.List;

import com.sjani.databindingexample.BR;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<Event> eventList;
    private int layoutId;
    private EventListViewModel viewModel;

    public ListAdapter(@LayoutRes int layoutId, EventListViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,viewType,parent,false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(viewModel,position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        if (eventList == null) {
            return 0;
        }
        return eventList.size();
    }

    public void swapResults(List<Event> result) {
        if (eventList == result) {
            return;
        }
        this.eventList = result;
        if (result != null) {
            this.notifyDataSetChanged();
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;


        public ListViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(EventListViewModel viewModel, Integer position) {
            viewModel.getEventAt(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }

}
