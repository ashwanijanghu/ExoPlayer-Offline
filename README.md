
# ExoPlayer-Offline
A ExoPlayer demo application changed for Offline Playback of DRM content.

## ExoPlayer Version - 2.2.0

In this sample we are using ExoPlayer - 2.2.0's in-built [OfflineLicenseHelper](https://github.com/google/ExoPlayer/blob/release-v2/library/core/src/main/java/com/google/android/exoplayer2/drm/OfflineLicenseHelper.java) to download and store license for offline playback.

### Configuration
In the repository inside [`sampleVideo`](https://github.com/ashwanijanghu/ExoPlayer-Offline/tree/master/sampleVideo) directory there are video & audio files with Manifest.mpd file also. For sample application to run without any code change You need to put these 3 files inside internal storage of Your android device. This is just a mock of downloaded sample content. These files are from sample itself, I just downloaded them for offline playback demonstartion.

In the first run application will download license and store it(so You need to be online in its first run) and then You can go offline and playback the content smoothly.

