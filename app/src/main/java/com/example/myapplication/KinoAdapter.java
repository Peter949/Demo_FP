package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class KinoAdapter extends RecyclerView.Adapter<KinoAdapter.ViewHolder>
{
    private List<ParamKino> kinos;
    private Context context;

    public KinoAdapter(List<ParamKino> kinos)
    {
        this.kinos = kinos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KinoAdapter.ViewHolder holder, int position)
    {
        ParamKino param = kinos.get(position);
        holder.text.setText(param.getName() + "\n" + param.getDescription());
        Picasso.with(context).load("http://cinema.areas.su/up/images/" + param.getPoster()).resize(700, 800).into(holder.image);
    }

    @Override
    public int getItemCount()
    {
        if(kinos == null)
        {
            return 0;
        }
        return kinos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView text;
        private ImageView image;
        ViewHolder(View view)
        {
            super(view);
            text = view.findViewById(R.id.textView);
            image = view.findViewById(R.id.imageView);
        }
    }
}
