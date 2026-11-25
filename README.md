# NAT TV

Native Kotlin Android IPTV player foundation for Android phones and Android TV.

## Current foundation
- Jetpack Compose obsidian / champagne-gold visual shell
- Phone navigation and Android TV sidebar navigation
- Media3 / ExoPlayer playback manager with explicit release handling
- Initial multi-account, channel and EPG domain models
- Room and DataStore dependencies included for planned local persistence

## Build
The GitHub Actions workflow builds a debug APK on pushes to main. Download it from the workflow run's Artifacts section.

## Current implementation status
This is a starter foundation, not a complete IPTV application. Playlist importing, Xtream authentication, XMLTV parsing, Room entities/DAOs, profile management, parental controls, recommendations, weather, notifications and the full playback interface remain to be implemented.

Only connect IPTV sources and streams that you are authorized to access. Cleartext HTTP traffic is disabled by default.

## Versions
- Android Gradle Plugin: 8.13.2
- Kotlin: 2.2.20
- Compile / target SDK: 36
- Minimum SDK: 23
- Media3: 1.11.1

## Release signing
Release signing is intentionally not configured with a committed key. Generate a private keystore and store it securely before publishing. Never commit keystore files or passwords to GitHub.