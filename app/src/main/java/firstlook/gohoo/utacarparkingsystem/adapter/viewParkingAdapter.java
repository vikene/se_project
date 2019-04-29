package firstlook.gohoo.utacarparkingsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import firstlook.gohoo.utacarparkingsystem.R;
import firstlook.gohoo.utacarparkingsystem.anant_view_selected_parking_spot;
import firstlook.gohoo.utacarparkingsystem.model.parkingSpot;
import firstlook.gohoo.utacarparkingsystem.parking_user_view_my_selected_reservation;
import firstlook.gohoo.utacarparkingsystem.parking_user_view_selected_parking_option;
import firstlook.gohoo.utacarparkingsystem.viewHolder.viewParking;

public class viewParkingAdapter extends RecyclerView.Adapter<viewParking> implements View.OnClickListener, Filterable {
    List<parkingSpot> spots;
    List<parkingSpot> spots_full;
    LayoutInflater mlayout;
    Context context;
    String current;
    Boolean viewed;

    public void setFilter(String val){
        this.current = val;
    }

    public void setViewed(Boolean val){
        this.viewed = val;
    }
    public void update(){
        this.notifyDataSetChanged();
    }
    public  viewParkingAdapter(List<parkingSpot> spots, Context context){
        mlayout = LayoutInflater.from(context);
        this.spots = spots;
        this.spots_full = spots;
        this.context = context;
        this.viewed = true;

    }
    @NonNull
    @Override
    public viewParking onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayout.inflate(R.layout.parkingspotitem,parent,false);
        return  new viewParking(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewParking holder, int position) {
        final parkingSpot ps = this.spots.get(position);
        holder.setAreaname("Parking Area Name: "+ps.getArea());
        holder.setSpot("Parking spot: "+ps.getSpot());
        holder.setType("Parking Type: "+ps.getType());
        if(this.viewed){
            //user
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent vi = new Intent(view.getContext(), parking_user_view_selected_parking_option.class);
                    vi.putExtra("key",ps.getKey());
                    vi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(vi);

                }
            });
        }
        else{
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent vi = new Intent(view.getContext(), anant_view_selected_parking_spot.class);
                    vi.putExtra("key",ps.getKey());
                    vi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(vi);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.spots.size();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<parkingSpot> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(spots_full);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                if(filterPattern.length() != 0){
                    for (parkingSpot item : spots_full) {
                        if(current.equals("time")){
                            if (item.getStarttime().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }
                        }
                        else if(current.equals("area")){
                            if (item.getArea().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }

                        }else if(current.equals("duration")){
                            if (item.getDuration().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }

                        }

                    }
                }
                else{
                    filteredList.addAll(spots_full);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            spots = new ArrayList<>();
            spots.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
