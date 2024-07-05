import app.moviebase.tmdb.Tmdb3
import app.moviebase.tmdb.model.TmdbMoviePageResult
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object MoviesComponent {

    val tmdbApi = Tmdb3 {
        tmdbApiKey = "API_KEY"

        httpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }





}
