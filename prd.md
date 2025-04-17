Aşağıda, yazmak istediğin Android uygulaması için detaylı bir PRD (Product Requirements Document - Ürün Gereksinim Dokümanı) yer alıyor. Bu belgeyi teknik ekip, tasarımcılar veya yatırımcılar ile paylaşarak proje hakkında net ve kapsamlı bir şekilde bilgi verebilirsin.

---

## 📱 **Uygulama Adı (Öneri): Nusayri Mirası**

---

### 🎯 **Amaç**

Bu mobil uygulama, Nusayrilerin (Arap Alevileri) tarihini, kültürünü, dini pratiklerini ve dil yapılarını yaşatmak ve yeni nesillere aktarmak amacıyla geliştirilmiştir. Uygulama, kullanıcıların hem bilgi edinmesini hem de günlük yaşamlarında kültürel bağlarını güçlendirmesini hedefler.

---

### 👥 **Hedef Kitle**
- Türkiye’de ve dünyada yaşayan Nusayriler (özellikle genç nesil)
- Araştırmacılar, akademisyenler, tarihçiler
- Kültürel çeşitliliğe ilgi duyan genel kullanıcılar

---

### 🧩 **Temel Özellikler**

#### 1. 📖 **Tarih ve Kültür Bölümü**
- Nusayrilerin tarihsel süreci (başlangıcından günümüze)
- Önemli tarihi kişiler
- Coğrafi yayılım (Samandağ, Antakya, Lübnan, vb.)
- Fotoğraf ve belge galerisi (multimedya destekli)

#### 2. 📅 **Dini Günler ve Sayacı**
- Arap Aleviliğinde önemli dini günlerin listesi (ör: Gadir-i Hum, Mübayaat Günü, Nevruz vs.)
- Her dini günün anlamı, tarihi ve nasıl kutlandığına dair bilgiler
- Geriye sayım: “Bir sonraki önemli güne kalan süre”
- Bildirim desteği: “Bugün … günü! Anlamını öğrenmek ister misin?”

#### 3. 🗣️ **Çeviri ve Konuşma Rehberi**
- **Türkçe - Arapça karşılıklı çeviri** (özellikle Samandağ / Antakya ağızlarına özgü)
- Kategoriler:
  - Samandağ / Antakya Ağızlarına Özgü Kalıplar
  - Dini İfadeler
  - Kısa Komutlar & Tepkiler
  - Ev ve Aile Ortamı
  - Zaman ve Günlük Yaşam
  - Duygular ve Tepkiler
  - Sık Duyulan Söz Kalıpları
  - Aile ve İnsanlar
  - Temel Kelimeler ve İfadeler


#### 4. 🎨 **Modern ve Kullanıcı Dostu Arayüz**
- Temiz ve minimalist tasarım
- Koyu / açık tema desteği
- Türkçe ve Arapça dil seçenekleri (isteğe bağlı sistem diliyle uyumlu)
- Responsive yapıda tasarım (tablet ve telefon uyumu)

---

### 🔧 **Teknik Gereksinimler**

- **Platform**: Android (Java )
- **Backend**: Firebase / Node.js + MongoDB / Spring Boot + PostgreSQL (içerik yönetimi için)
- **Bildirim Sistemi**: Firebase Cloud Messaging
- **Veri Saklama**: Yerel veritabanı (Room, SQLite) + opsiyonel çevrimdışı mod

---

### 📈 **Geliştirme Aşamaları**

1. **Analiz ve İçerik Toplama**  
   - Dini günlerin ve tarihsel verilerin akademik doğrulaması  
   - Ağız kalıplarının doğru şekilde derlenmesi

2. **Tasarım (UI/UX)**  
   - Prototip hazırlanması (Figma önerilir)  
   - Kullanıcı testleri

3. **Geliştirme**  
   - Backend API’lerin oluşturulması  
   - Mobil uygulama arayüzünün kodlanması  
   - Bildirim ve sayaç sistemlerinin entegrasyonu

4. **Test ve Yayın**  
   - Beta test kullanıcılarıyla deneme  
   - Google Play Store’da yayınlama

---

### 📝 **Gelecekteki Geliştirmeler (Opsiyonel)**

- Kullanıcılar arası söz ekleme / paylaşım (community tabanlı içerik)
- Podcast veya video içerikler (yaşlılardan ağız kalıpları / dini anlatılar)
- Harita üzerinde geleneksel köyler ve bilgileri

---

İstersen bu PRD'yi PDF haline çevirebilir ya da geliştirici ekibine özel teknik dökümana dönüştürebilirim. Uygulamanın ismini de birlikte belirleyebiliriz. Başka bir özellik eklemek ister misin?