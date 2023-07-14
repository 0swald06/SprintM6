package com.example.sprintm6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sprintm6.ViewModel.PhonesViewModel
import com.example.sprintm6.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit  var  mBinding: FragmentSecondBinding
    private val  mViewModel : PhonesViewModel  by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var phoneId : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let { bundle ->

            phoneId = bundle.getString("phoneId")
            Log.d("seleccion2",phoneId.toString())

        }
        phoneId?.let { id ->
            mViewModel.getPhonesDetailByIdFromInternet(id)
        }

        mViewModel.getPhonesDetail().observe(viewLifecycleOwner,{

            Log.d("seleccion3",phoneId.toString())
            var id= it.id
            var name = it.name
            Glide.with(mBinding.itemImagen).load(it.image).into(mBinding.itemImagen)
            mBinding.itemName.text=it.name
            mBinding.itemValor.text="Valor: "+it.price
            mBinding.itemDescription.text=it.description
            mBinding.itemLastValor.text=it.lastPrice



            mBinding.btMail.setOnClickListener{


                val mintent= Intent(Intent.ACTION_SEND)
                mintent.data= Uri.parse("mailto")
                mintent.type="text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("admisión@centrofuturo.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Solicito información sobre este curso"+ id
                )


                mintent.putExtra(

                    Intent.EXTRA_TEXT,"Hola\n"+

                            "Quisiera pedir información sobre este curso " + name + " ,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."



                )
                try {
                    startActivity(mintent)
                } catch (e: Exception) {

                    Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                }
            }



        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
      //  _binding = null
    }
}