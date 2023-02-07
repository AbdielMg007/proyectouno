package com.mg.proyectouno.view.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isInvisible
import com.google.firebase.auth.FirebaseAuth
import com.mg.proyectouno.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(Color.parseColor("#d7b52c"))
        binding.progressBar.isInvisible = true
        septup()
    }

    public override fun onResume() {
        super.onResume()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val pass = Intent(this, MainActivity::class.java)
            startActivity(pass)
            finish()
        } else {
            septup()
        }
    }

    private fun septup(){
        binding.loginButton.setOnClickListener{
            binding.progressBar.isInvisible = false
            if(binding.user.text.isNotEmpty() && binding.pass.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.user.text.toString(), binding.pass.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful){
                            val nextScreen = Intent(this, MainActivity::class.java)
                            binding.progressBar.isInvisible = true
                            startActivity(nextScreen)
                            finish()
                        }else{
                            binding.progressBar.isInvisible = true
                            showAlert()
                        }
                    }
            }else{
                binding.progressBar.isInvisible = true
                Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show()
            }

        }
        binding.tvCreate.setOnClickListener{
            binding.progressBar.isInvisible = false
            if(binding.user.text.isNotEmpty() && binding.pass.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(binding.user.text.toString(), binding.pass.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful){
                            val nextScreen = Intent(this, MainActivity::class.java)
                            binding.progressBar.isInvisible = true
                            startActivity(nextScreen)
                            finish()
                        }else{
                            binding.progressBar.isInvisible = true
                            showAlert()
                        }
                    }
            }else{
                binding.progressBar.isInvisible = true
                Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvForgot.setOnClickListener{
            binding.progressBar.isInvisible = false
            if(binding.user.text.isNotEmpty()){
                binding.progressBar.isInvisible = true
                FirebaseAuth.getInstance().sendPasswordResetEmail(binding.user.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful)
                        Toast.makeText(this,"Se ah enviado un correo para restablecer su contraseña",Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this,"Ah ocurrido un error, revise si el correo es autentico",Toast.LENGTH_SHORT).show()
                }
            }else{
                binding.progressBar.isInvisible = true
                Toast.makeText(this,"Ingrese su correo que registro",Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Verifiique si su usuario o contraseña son correctos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun Activity.setStatusBarColor(@ColorInt color: Int) {
        val window = getWindow()
        val decorView: View = window.getDecorView()
        val wic = WindowInsetsControllerCompat(window, decorView)
        wic.isAppearanceLightStatusBars = ColorUtils.calculateLuminance(color) > 0.5
        window.statusBarColor = color
    }
}