# Uygulamayı Çalıştırma Talimatları

Bu belge, uygulamamızı nasıl düzgün bir şekilde çalıştıracağınızı açıklar. Aşağıdaki adımları sırayla takip edin.

## Ön Koşullar

- JDK 17 yüklü olmalıdır (`C:\Program Files\Java\jdk-17` konumunda)
- Android Studio kurulu olmalıdır
- Gradle 7.6.1 veya daha yüksek bir sürüm
- Uygulamayı test etmek için bir Android cihaz veya emülatör

## Kurulum ve Derleme Adımları

### 1. Projeyi Temizleme

Önce projeyi temizleyin:

```bash
.\clean-cache.bat
.\clean.bat
```

### 2. Uygulamayı Derleme

```bash
.\build.bat
```

Derleme başarılı olmalı ve herhangi bir hata çıktısı göstermemelidir.

### 3. Android Studio ile Çalıştırma

1. Android Studio'yu başlatın
2. **File > Open** seçeneği ile projeyi açın
3. Gradle senkronizasyonunun tamamlanmasını bekleyin
4. Açılır menüden emülatör veya bağlı cihazı seçin
5. Yeşil "Run" butonuna basın veya **Shift+F10** tuşlarına basın

### 4. Komut Satırı ile Çalıştırma

Bir Android cihaz veya emülatör bağlandığından emin olun:

```bash
adb devices
```

Cihazların listesini görmelisiniz. Eğer cihaz varsa, uygulamayı yükleyin:

```bash
.\run.bat
```

## Yaygın Hatalar ve Çözümleri

### Uygulama Çöküyor

Uygulama çalışma sırasında çöküyorsa:

1. Android Studio'da **Logcat** sekmesini açın
2. Kırmızı renkli hatayı bulun
3. Sorun genellikle şunlardan biri olabilir:
   - Eksik veya yanlış bağımlılıklar
   - Hatalı kaynak dosyaları
   - Android Manifest sorunları
   - ID uyumsuzlukları

### Çözüm İçin Yapılanlar

Bu projede aşağıdaki iyileştirmeleri yaptık:

1. **MainActivity.java** ve **ReligiousDaysActivity.java** dosyalarına kapsamlı hata yönetimi ekledik
2. **AndroidManifest.xml** düzeltildi ve tüm Activity'ler doğru şekilde tanımlandı
3. **build.gradle** dosyasında applicationId "com.example.nsyri" olarak güncellendi
4. Firebase geçici olarak devre dışı bırakıldı
5. XML dosyalarında ID uyumsuzlukları giderildi

Bu değişiklikler, uygulamanın çökmesine neden olan ana sorunları çözmelidir.

## Geri Bildirim

Herhangi bir sorun yaşarsanız veya ek yardıma ihtiyacınız varsa, lütfen iletişime geçin. 