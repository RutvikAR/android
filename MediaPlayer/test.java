import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity
	extends FragmentActivity implements OnMapReadyCallback {

	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
	
		SupportMapFragment mapFragment
			= (SupportMapFragment)
				getSupportFragmentManager()
					.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	
	@Override public void onMapReady(GoogleMap googleMap)
	{
		mMap = googleMap;
		LatLng sydney = new LatLng(-34, 151);
		mMap.addMarker(new MarkerOptions()
						.position(sydney)
						.title("Marker in Sydney")
						
						.icon(BitmapFromVector(
							getApplicationContext(),
							R.drawable.ic_flag)));
		mMap.moveCamera(
			CameraUpdateFactory.newLatLng(sydney));
	}

	private BitmapDescriptor
	BitmapFromVector(Context context, int vectorResId)
	{
		// below line is use to generate a drawable.
		Drawable vectorDrawable = ContextCompat.getDrawable(
			context, vectorResId);

		vectorDrawable.setBounds(
			0, 0, vectorDrawable.getIntrinsicWidth(),
			vectorDrawable.getIntrinsicHeight());
		
		Bitmap bitmap = Bitmap.createBitmap(
			vectorDrawable.getIntrinsicWidth(),
			vectorDrawable.getIntrinsicHeight(),
			Bitmap.Config.ARGB_8888);

		
		Canvas canvas = new Canvas(bitmap);

		
		vectorDrawable.draw(canvas);

		return BitmapDescriptorFactory.fromBitmap(bitmap);
	}
}
