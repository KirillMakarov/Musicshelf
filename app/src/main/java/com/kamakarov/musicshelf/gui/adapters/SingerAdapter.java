package com.kamakarov.musicshelf.gui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kamakarov.musicshelf.R;
import com.kamakarov.musicshelf.model.Singer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public final class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.UnitViewHolder> {

    Context context;
    List<Singer> singerList;

    public SingerAdapter(Context context, List<Singer> singerList) {
        this.context = context;
        this.singerList = singerList;
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singer_item, parent, false);
        return new UnitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UnitViewHolder holder, int position) {
        Singer singer = singerList.get(position);
        holder.singerName.setText(singer.getName());
    }

    @Override
    public int getItemCount() {
        return singerList.size();
    }

    public class UnitViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.singer_name)
        TextView singerName;

        public UnitViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
