package com.example.randomuserapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.randomuserapp.retrofit.User
import com.squareup.picasso.Picasso

class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.getSerializableExtra("user") as User

        val userImageView  = findViewById<ImageView>(R.id.imageView_user)
        Picasso.get().load(user.picture.large).into(userImageView)

        val fullNameTextView = findViewById<TextView>(R.id.textView_fullName)
        fullNameTextView.text = "${user.name.title} ${user.name.first} ${user.name.last}"

        val streetTextView = findViewById<TextView>(R.id.textView_street)
        streetTextView.text = "Улица: ${user.location.street.number} ${user.location.street.name}"

        val cityTextView = findViewById<TextView>(R.id.textView_city)
        cityTextView.text = "Город: " + user.location.city

        val stateTextView = findViewById<TextView>(R.id.textView_state)
        stateTextView.text = "Штат: " + user.location.state

        val countryTextView = findViewById<TextView>(R.id.textView_country)
        countryTextView.text = "Страна: " + user.location.country

        val postcodeTextView = findViewById<TextView>(R.id.textView_postcode)
        postcodeTextView.text = "Почтовый индекс: " + user.location.postcode

        val coordinatesTextView = findViewById<TextView>(R.id.textView_coordinates)
        coordinatesTextView.text = "Координаты: ${user.location.coordinates.latitude} ${user.location.coordinates.longitude}"
        coordinatesTextView.setOnClickListener {
            val coordinatesIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:${user.location.coordinates.latitude},${user.location.coordinates.longitude}")
            }
            try{
                startActivity(coordinatesIntent)
            }
            catch (e: ActivityNotFoundException){
                Toast.makeText(this@UserDetailActivity, "Приложение для работы с координатами не найдено", Toast.LENGTH_SHORT).show()
            }
        }

        val timezoneTextView = findViewById<TextView>(R.id.textView_timezone)
        timezoneTextView.text = "Часовой пояс: ${user.location.timezone.offset}, ${user.location.timezone.description}"

        val emailTextView = findViewById<TextView>(R.id.textView_email)
        emailTextView.text = "Почта: " + user.email
        emailTextView.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${user.email}")
            }
            try{
                startActivity(emailIntent)
            }
            catch (e: ActivityNotFoundException){
                Toast.makeText(this@UserDetailActivity, "Почтовое приложение не найдено", Toast.LENGTH_SHORT).show()
            }
        }

        val phoneTextView = findViewById<TextView>(R.id.textView_phone)
        phoneTextView.text = "Телефон: " + user.phone
        phoneTextView.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${user.phone}")
            }
            try{
                startActivity(phoneIntent)
            }
            catch (e: ActivityNotFoundException){
                Toast.makeText(this@UserDetailActivity, "Приложение для совершения звонка не найдено", Toast.LENGTH_SHORT).show()
            }
        }

        val dobTextView = findViewById<TextView>(R.id.textView_dob)
        dobTextView.text = "Дата рождения: " + user.dob.date + ", возраст: " + user.dob.age

        val genderTextView = findViewById<TextView>(R.id.textView_gender)
        genderTextView.text = "Пол: " + user.gender

        val natTextView = findViewById<TextView>(R.id.textView_nat)
        natTextView.text = "Национальность: " + user.nat
    }
}