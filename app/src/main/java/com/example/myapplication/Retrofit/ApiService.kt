import com.example.myapplication.Question
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("preguntes") // Ajusta l'URL segons el teu servidor
    fun getQuestions(): Call<List<Question>>
}