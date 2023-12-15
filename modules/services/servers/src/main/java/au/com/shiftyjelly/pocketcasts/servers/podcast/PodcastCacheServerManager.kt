package au.com.shiftyjelly.pocketcasts.servers.podcast

import au.com.shiftyjelly.pocketcasts.models.entity.Podcast
import au.com.shiftyjelly.pocketcasts.models.entity.PodcastEpisode
import au.com.shiftyjelly.pocketcasts.models.entity.PodcastRatings
import au.com.shiftyjelly.pocketcasts.servers.discover.EpisodeSearch
import io.reactivex.Single
import retrofit2.Response

interface PodcastCacheServerManager {
    fun getPodcast(podcastUuid: String): Single<Podcast>
    fun getPodcastAndEpisodeSingle(podcastUuid: String, episodeUuid: String): Single<Podcast>
    suspend fun getPodcastAndEpisode(podcastUuid: String, episodeUuid: String): Podcast
    fun searchEpisodes(podcastUuid: String, searchTerm: String): Single<List<String>>
    fun searchEpisodes(searchTerm: String): Single<EpisodeSearch>
    suspend fun getPodcastResponse(podcastUuid: String): Response<PodcastResponse>
    suspend fun getPodcastResponseByUrl(url: String): Response<PodcastResponse>
    suspend fun getPodcastResponseCache(podcastUuid: String): Response<PodcastResponse>
    suspend fun getPodcastsUpdate(podcastsLastModified: List<PodcastAndLastModified>): PodcastsUpdateResponse
    fun getPodcastResponseSingle(podcastUuid: String): Single<Response<PodcastResponse>>
    suspend fun getPodcastRatings(podcastUuid: String): PodcastRatings
    suspend fun getShowNotes(podcastUuid: String): ShowNotesResponse
    suspend fun getShowNotesCache(podcastUuid: String): ShowNotesResponse?
    suspend fun getEpisodeUrl(episode: PodcastEpisode): String?
}
