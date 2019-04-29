package firstlook.gohoo.utacarparkingsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import firstlook.gohoo.utacarparkingsystem.R;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;
import firstlook.gohoo.utacarparkingsystem.noshow;
import firstlook.gohoo.utacarparkingsystem.parking_user_view_my_selected_reservation;
import firstlook.gohoo.utacarparkingsystem.utsab_view_selected_reservation_by_parking_manager;
import firstlook.gohoo.utacarparkingsystem.viewHolder.viewParking;
import firstlook.gohoo.utacarparkingsystem.viewHolder.viewreservations;
import firstlook.gohoo.utacarparkingsystem.view_my_no_shows_violations;

public class viewReservationsAdapter extends RecyclerView.Adapter<viewreservations> implements View.OnClickListener {
    List<parkingSpot> spots;
    LayoutInflater mlayout;
    Context context;
    public  Boolean calling;
    public Boolean second;
    public  viewReservationsAdapter(List<parkingSpot> spots, Context context){
        mlayout = LayoutInflater.from(context);
        this.spots = spots;
        this.context = context;
        calling = false;
        second = false;
    }

    @NonNull
    @Override
    public viewreservations onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayout.inflate(R.layout.viewreservations,parent,false);
        return  new viewreservations(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewreservations holder, int position) {
        final parkingSpot ps = this.spots.get(position);
        holder.setDate(ps.getDate());
        holder.setTransactionid("Transactionid: #"+ps.getKey().substring(ps.getKey().length()-4));
        if(calling){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myintent = new Intent(view.getContext(), utsab_view_selected_reservation_by_parking_manager.class);
                    myintent.putExtra("key", ps.getKey());
                    myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(myintent);
                }
            });
        }
        else{
            if(second){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myintent = new Intent(view.getContext(), noshow.class);
                        myintent.putExtra("key", ps.getKey());
                        myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(myintent);
                    }
                });
            }
            else {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myintent = new Intent(view.getContext(), parking_user_view_my_selected_reservation.class);
                        myintent.putExtra("key", ps.getKey());
                        myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(myintent);
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return this.spots.size();
    }

    @Override
    public void onClick(View view) {

    }
}
