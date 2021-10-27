package com.example.dados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dados.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var  geradorRandomico: Random
    private lateinit var  settingsActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        geradorRandomico = Random(System.currentTimeMillis())

        activityMainBinding.jogarDadoBt.setOnClickListener {
            val resultado: Int = geradorRandomico.nextInt(1..6)
            "A face sorteada foi $resultado".also {
                activityMainBinding.resultadoTv.text = it
            }
            val nomeImagem: String = "dice_${resultado}"
            activityMainBinding.resultadoIv.setImageResource(
                resources.getIdentifier(nomeImagem, "mipmap", packageName)
            )
        }

        settingsActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
            if(result.resultCode == RESULT_OK){
                //modificacao da minha view
                if(result.data != null) {
                    val configuracao: Configuracao? = result.data?.getParcelableExtra<Configuracao>(Intent.EXTRA_USER)

                    //******** EXERCICIO MODIFICACAO DA VIEW *******


                }
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settingsMi){
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            settingsActivityResultLauncher.launch(settingsIntent)
            return true
        }
        return false
    }
}