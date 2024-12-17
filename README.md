# RestaurantApp

RestaurantApp, Jetpack Compose kullanımını öğrenme kapsamında geliştirilen bir **Android uygulamasıdır**. Bu uygulama Compose UI kullanarak restoran menüsünü listeleme, favorilere ekleme ve sipariş verme gibi temel özellikler sağlar.
---

## Özellikler
- **Menü Görüntüleme**: Restoran menüsündeki yemekler, resim, açıklama ve fiyat bilgisi ile listelenir.
- **Favorilere Ekleme**: Kullanıcı, yemekleri favorilere ekleyebilir veya favorilerden çıkarabilir.
- **Sipariş Verme**: Her yemeğin yanında sipariş butonu yer alır.
- **Sayfa Geçişi**: Ana menü ve favoriler sayfaları arasında geçiş yapılabilir.
- **Modern UI**: Jetpack Compose kullanılarak modern ve sade bir arayüz tasarlandı.

---

## Teknolojiler
Bu projede kullanılan teknolojiler ve araçlar:
- **Kotlin**: Uygulama dili olarak Kotlin kullanıldı.
- **Jetpack Compose**: UI tasarımı için modern Android araç kiti.
- **Android Studio**: Geliştirme ortamı.
- **MVVM Mimari**: ViewModel ile veri yönetimi sağlandı.

---

## Kurulum
1. **Projeyi klonlayın**:
   ```bash
   git clone https://github.com/zulfub/RestaurantApp.git
   ```
2. **Android Studio** ile projeyi açın.
3. **Gerekli bağımlılıkları yükleyin**.
4. **Cihaz veya Emulator** üzerinde çalıştırın.

---

## Kullanım
1. **Uygulamayı Başlatın**.
2. Menüde listelenen yemekleri görüntüleyin.
3. Yemekleri favorilere eklemek için kalp ikonuna tıklayın.
4. "Order" butonu ile sipariş verin.
5. Favoriler sayfasına geçmek için üstteki kalp ikonuna tıklayın.


## Proje Yapısı
```
RestaurantApp/
│-- MainActivity.kt         # Ana giriş noktası
│-- AppNavigation.kt        # Sayfalar arası geçiş
│-- MainMenu.kt             # Menü listesi ekranı
│-- Favorites.kt            # Favoriler ekranı
│-- viewmodel/              # ViewModel sınıfı ve veri yönetimi
│-- res/                    # Drawable, renkler ve layout kaynakları
└-- README.md               # Proje açıklamaları
```

---
