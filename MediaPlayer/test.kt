package com.example.map_custonm_marker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

// Importing maps libraries 
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.map_custonm_marker.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

// class 
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

	private lateinit var mMap: GoogleMap
	private lateinit var binding: ActivityMapsBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMapsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		val mapFragment = supportFragmentManager
			.findFragmentById(R.id.map) as SupportMapFragment
		mapFragment.getMapAsync(this)
	}

	override fun onMapReady(googleMap: GoogleMap) {
		mMap = googleMap

		// Add a marker in Sydney and move the camera
		val sydney = LatLng(-34.0, 151.0)

		val marker=MarkerOptions().position(sydney).title("Marker in Sydney")
		//set custom icon
		marker.icon(BitmapFromVector(getApplicationContext(), R.drawable.baseline_flag_24))
		//add marker
		mMap.addMarker(marker)

		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
	}
private fun BitmapFromVector(context:Context,vectorResId:Int): BitmapDescriptor? {
	//drawable generator
	var vectorDrawable: Drawable
	vectorDrawable= ContextCompat.getDrawable(context,vectorResId)!!
	vectorDrawable.setBounds(0,0,vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight)
	//bitmap genarator
	var bitmap:Bitmap
	bitmap= Bitmap.createBitmap(vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
	//canvas genaret
	var canvas:Canvas
	//pass bitmap in canvas constructor
	canvas= Canvas(bitmap)
	//pass canvas in drawable
	vectorDrawable.draw(canvas)
		//return BitmapDescriptorFactory
	return BitmapDescriptorFactory.fromBitmap(bitmap)
}
}
