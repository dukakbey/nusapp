package com.example.nsyri;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.google.android.material.card.MaterialCardView;

public class CategoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        try {
            // Toolbar'ı ayarla
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

            // Intent'ten kategori adını al
            String categoryName = getIntent().getStringExtra("CATEGORY_NAME");
            if (categoryName == null) {
                categoryName = "Kategori";
            }
            
            // Kategori adını başlık olarak ayarla
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(categoryName);
            }

            // Kategori açıklaması
            TextView categoryDescription = findViewById(R.id.categoryDescription);
            if (categoryDescription != null) {
                categoryDescription.setText(categoryName + " kategorisi, Nusayrice-Türkçe çevirileri içermektedir. "
                        + "Bu bölümde günlük hayatta kullanılan önemli ifadelerin çevirilerini bulabilirsiniz.");
            }

            // Çeviriler başlığı
            TextView categoryDetailText = findViewById(R.id.categoryDetailText);
            if (categoryDetailText != null) {
                categoryDetailText.setText(categoryName + " Kategorisi Çevirileri");
            }
            
            // Kategori içeriğini güncelle
            updateCategoryContent(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Kategori içeriğini güncelle
    private void updateCategoryContent(String categoryName) {
        try {
            LinearLayout contentLayout = findViewById(R.id.translationContentLayout);
            if (contentLayout == null) return;
            
            // Mevcut içeriği temizle
            contentLayout.removeAllViews();
            
            // Samandağ/Antakya Ağızları kategorisi için özel içerik
            if (categoryName.equals(getString(R.string.category_dialect))) {
                // Kategori açıklamasını güncelle
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Samandağ ve Antakya bölgelerinde kullanılan yerel ağızlar ve deyimler, bölgenin kültürel mirasının önemli bir parçasıdır. Bu bölümde bölgeye özgü yaygın kullanılan ifadeleri bulabilirsiniz.");
                }
                
                // Özel çevirileri ekle
                addTranslationCard(contentLayout, "وين رايح؟", "Nereye gidiyorsun?", "weyn rāyeḥ?", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "طلع هاد!", "Baksana şuna!", "ṭalle' hād!", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "شو عم تعمل هنيك؟", "Napıyon orda?", "şū 'am ta'mel hnik?", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "نطّر شوي", "Biraz bekle", "naṭṭer şway", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "اجيت", "Ben geldim", "ijeet", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "منشوفك", "Görüşürüz", "menşūfak", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "روّق", "Yavaş ol", "rawwaʼ", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "شو في ما في؟", "Ne var ne yok?", "şū fī mā fī?", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "ما بعرف والله", "Bilmiyorum valla", "mā ba'ref wallāh", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "عن جد؟", "Hadi canım!", "'an jadd?", R.drawable.ic_dialect);
                
            } else if (categoryName.equals(getString(R.string.category_religious))) {
                // Dini İfadeler kategorisi
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Dini ifadeler, topluluk içinde önemli bir yer tutar. Bu kategori, Nusayri topluluğunda kullanılan yaygın dini terimler ve ifadeleri içerir.");
                }
                
                addTranslationCard(contentLayout, "الله يرضى عليك", "Allah razı olsun", "Allah yirḍa 'aleyk", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "الله يحميك", "Allah korusun", "Allah yeḥmīk", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "الله يتقبل", "Allah kabul etsin", "Allah yetʼabbal", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "دعواتي معك", "Dualarım seninle", "da'wātī ma'ak", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "مبروك", "Hayırlı olsun", "mabrūk", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "الله يرحمو", "Başın sağ olsun", "Allah yerḥmo", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "الله يسامحو", "Allah affetsin", "Allah yesāmeḥo", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "إن شاء الله", "İnşallah", "in shāʼ Allah", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "ما شاء الله", "Maşallah", "mā shāʼ Allah", R.drawable.ic_religious);
                addTranslationCard(contentLayout, "الحمد لله", "Elhamdülillah", "il-ḥamd lillāh", R.drawable.ic_religious);
                
            } else if (categoryName.equals(getString(R.string.category_family))) {
                // Aile ve İnsanlar kategorisi
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Aile üyeleri ve kişilerle ilgili terimler, günlük iletişimin önemli bir parçasıdır. Bu kategori, aile bireyleri ve kişilerle ilgili ifadeleri içerir.");
                }
                
                addTranslationCard(contentLayout, "أب / بيّي", "Baba", "ab / bayyi", R.drawable.ic_home);
                addTranslationCard(contentLayout, "أم / إمّي", "Anne", "imm / immi", R.drawable.ic_home);
                addTranslationCard(contentLayout, "أخ / أخوي", "Erkek kardeş", "akh / khayy", R.drawable.ic_home);
                addTranslationCard(contentLayout, "أخت / إختي", "Kız kardeş", "ikht / ikhti", R.drawable.ic_home);
                addTranslationCard(contentLayout, "عم", "Amca", "'amm", R.drawable.ic_home);
                addTranslationCard(contentLayout, "خال", "Dayı", "khāl", R.drawable.ic_home);
                addTranslationCard(contentLayout, "عمة", "Hala", "'amme", R.drawable.ic_home);
                addTranslationCard(contentLayout, "خالة", "Teyze", "khāle", R.drawable.ic_home);
                
            } else if (categoryName.equals(getString(R.string.category_home_family))) {
                // Ev ve Aile Ortamı kategorisi
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Ev ve aile hayatı, günlük yaşamın en önemli parçasıdır. Bu kategori, ev ve aile ortamında kullanılan temel ifadeleri içerir.");
                }
                
                addTranslationCard(contentLayout, "بيت", "Ev", "beyt", R.drawable.ic_home_family);
                addTranslationCard(contentLayout, "أوضة", "Oda", "ōḍa", R.drawable.ic_home_family);
                addTranslationCard(contentLayout, "مطبخ", "Mutfak", "maṭbakh", R.drawable.ic_home_family);
                addTranslationCard(contentLayout, "الأكل جاهز", "Yemek hazır", "l-akl jāhiz", R.drawable.ic_home_family);
                addTranslationCard(contentLayout, "تعا عالسفرة", "Sofraya gel", "ta'a 'a-s-sufra", R.drawable.ic_home_family);
                addTranslationCard(contentLayout, "شبعت؟", "Doydun mu?", "şibi't?", R.drawable.ic_home_family);
                addTranslationCard(contentLayout, "يسلم إيديك", "Ellerine sağlık", "yislam īdeyk", R.drawable.ic_home_family);
                addTranslationCard(contentLayout, "يلا نام", "Hadi yat artık", "yalla nām", R.drawable.ic_home_family);
                
            } else if (categoryName.equals(getString(R.string.category_phrases))) {
                // Sık Duyulan Söz Kalıpları kategorisi
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Günlük hayatta sık kullanılan söz kalıpları, iletişimin vazgeçilmez öğeleridir. Bu kategori, günlük kullanımdaki yaygın ifadeleri içerir.");
                }
                
                addTranslationCard(contentLayout, "سكت", "Sus", "üsḳut (erkek) / üsḳuti (kadın)", R.drawable.ic_commands);
                addTranslationCard(contentLayout, "نطّر شوي", "Bekle biraz", "naṭṭer şway", R.drawable.ic_commands);
                addTranslationCard(contentLayout, "وقف", "Dur!", "wʼaaf", R.drawable.ic_commands);
                addTranslationCard(contentLayout, "استعجل", "Acele et", "ista'jil", R.drawable.ic_commands);
                addTranslationCard(contentLayout, "يلا", "Hadi!", "yalla", R.drawable.ic_commands);
                addTranslationCard(contentLayout, "ما بصير", "Olmaz", "mā biṣīr", R.drawable.ic_commands);
                addTranslationCard(contentLayout, "بصير", "Olur", "biṣīr", R.drawable.ic_commands);
                
            } else if (categoryName.equals(getString(R.string.category_emotions))) {
                // Duygular ve Tepkiler kategorisi
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Duygular ve tepkiler, insani iletişimin özünü oluşturur. Bu kategori, duyguları ve tepkileri ifade eden yaygın söz kalıplarını içerir.");
                }
                
                addTranslationCard(contentLayout, "بحبك", "Seni seviyorum", "bḥebbak (erkek) / bḥebbik (kadın)", R.drawable.ic_emotions);
                addTranslationCard(contentLayout, "اشتقتلك", "Özledim", "iştaktillak", R.drawable.ic_emotions);
                addTranslationCard(contentLayout, "زعلان", "Kızgınım", "za'lān", R.drawable.ic_emotions);
                addTranslationCard(contentLayout, "مبسوط", "Mutluyum", "mabsūṭ", R.drawable.ic_emotions);
                addTranslationCard(contentLayout, "زعلان / حزين", "Üzgünüm", "za'lān / ḥazīn", R.drawable.ic_emotions);
                addTranslationCard(contentLayout, "خايف", "Korkuyorum", "khāyef", R.drawable.ic_emotions);
                addTranslationCard(contentLayout, "ما بعرف", "Bilmiyorum", "mā ba'ref", R.drawable.ic_emotions);
                addTranslationCard(contentLayout, "ما بتفرق", "Fark etmez", "mā btefrek", R.drawable.ic_emotions);
                
            } else if (categoryName.equals(getString(R.string.category_time_daily))) {
                // Zaman ve Günlük Hayat kategorisi
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Zaman kavramları ve günlük yaşamdaki temel ifadeler, sohbetin ve iletişimin önemli yapı taşlarıdır. Bu kategori, zaman ve günlük yaşamla ilgili temel terimleri içerir.");
                }
                
                addTranslationCard(contentLayout, "الصبح", "Sabah", "aṣ-ṣubeḥ", R.drawable.ic_time_daily);
                addTranslationCard(contentLayout, "الليل", "Gece", "l-leyl", R.drawable.ic_time_daily);
                addTranslationCard(contentLayout, "بكرا", "Yarın", "bukra", R.drawable.ic_time_daily);
                addTranslationCard(contentLayout, "اليوم", "Bugün", "il-yom", R.drawable.ic_time_daily);
                addTranslationCard(contentLayout, "مبارح", "Dün", "mbāreḥ", R.drawable.ic_time_daily);
                addTranslationCard(contentLayout, "هلّق", "Şimdi", "hallaʼ", R.drawable.ic_time_daily);
                addTranslationCard(contentLayout, "دغري", "Hemen", "doghrī", R.drawable.ic_time_daily);
                addTranslationCard(contentLayout, "تأخرنا", "Geç oldu", "tʼakharnā", R.drawable.ic_time_daily);
                
            } else if (categoryName.equals(getString(R.string.category_basic))) {
                // Temel Kelimeler ve İfadeler kategorisi
                TextView categoryDescription = findViewById(R.id.categoryDescription);
                if (categoryDescription != null) {
                    categoryDescription.setText("Temel kelimeler ve ifadeler, günlük konuşmanın yapı taşlarıdır. Bu kategori, en sık kullanılan temel kelimeleri ve ifadeleri içerir.");
                }
                
                addTranslationCard(contentLayout, "مرحبا", "Merhaba", "merhaba", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "كيفك؟", "Nasılsın?", "kifek? (erkek) / kifik? (kadın)", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "منيح", "İyiyim", "mniih (erkek) / mniiha (kadın)", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "شكراً", "Teşekkür ederim", "şukran", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "شو عم تعمل؟", "Ne yapıyorsun?", "şu am ta'mel? (erkek) / şu am ta'mli? (kadın)", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "تعا لهون", "Gel buraya", "taa la hoon", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "يلا نروح", "Hadi gidelim", "yalla nrūh", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "ايمتى جاي؟", "Ne zaman geliyorsun?", "emta jey?", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "أكلت؟", "Yemek yedin mi?", "akalt? (erkek) / akalti? (kadın)", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "ايه", "Evet", "ey", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "لا", "Hayır", "la", R.drawable.ic_dialect);
                
            } else {
                // Genel içerik
                addTranslationCard(contentLayout, "مرحبا", "Merhaba", "Marḥaba", R.drawable.ic_dialect);
                addTranslationCard(contentLayout, "شكراً", "Teşekkür ederim", "Šukran", R.drawable.ic_dialect);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Çeviri kartı ekle
    private void addTranslationCard(LinearLayout parentLayout, String originalText, String translatedText, String pronunciation, int iconResId) {
        try {
            MaterialCardView cardView = new MaterialCardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            cardParams.setMargins(0, 0, 0, 16); // Alt margin ekle
            cardView.setLayoutParams(cardParams);
            cardView.setCardElevation(4f);
            cardView.setRadius(8f);
            cardView.setUseCompatPadding(true);
            
            LinearLayout cardContent = new LinearLayout(this);
            cardContent.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            cardContent.setOrientation(LinearLayout.VERTICAL);
            cardContent.setPadding(32, 32, 32, 32);
            
            // İkon ve başlık için bir satır
            LinearLayout headerRow = new LinearLayout(this);
            headerRow.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            headerRow.setOrientation(LinearLayout.HORIZONTAL);
            headerRow.setGravity(android.view.Gravity.CENTER_VERTICAL);
            
            // İkon ekle
            ImageView iconView = new ImageView(this);
            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            iconParams.width = 48;
            iconParams.height = 48;
            iconParams.rightMargin = 16;
            iconView.setLayoutParams(iconParams);
            iconView.setImageResource(iconResId);
            iconView.setBackgroundResource(R.color.colorPrimary);
            iconView.setPadding(8, 8, 8, 8);
            
            // Orijinal metin başlığı
            TextView originalTextView = new TextView(this);
            originalTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            originalTextView.setText(originalText);
            originalTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
            originalTextView.setTextSize(16);
            originalTextView.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            
            // İkon ve başlığı satıra ekle
            headerRow.addView(iconView);
            headerRow.addView(originalTextView);
            
            // Çevrilmiş metin
            TextView translatedTextView = new TextView(this);
            translatedTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            translatedTextView.setText("Türkçe: " + translatedText);
            translatedTextView.setTextSize(14);
            translatedTextView.setPadding(0, 16, 0, 0);
            
            // Telaffuz metni
            TextView pronunciationTextView = new TextView(this);
            pronunciationTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            pronunciationTextView.setText("Okunuş: " + pronunciation);
            pronunciationTextView.setTextSize(12);
            pronunciationTextView.setTextAppearance(this, android.R.style.TextAppearance_Small);
            pronunciationTextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
            pronunciationTextView.setPadding(0, 8, 0, 0);
            
            // Görünümleri kart içeriğine ekle
            cardContent.addView(headerRow);
            cardContent.addView(translatedTextView);
            cardContent.addView(pronunciationTextView);
            
            // Kart içeriğini karta ekle
            cardView.addView(cardContent);
            
            // Kartı ana layout'a ekle
            parentLayout.addView(cardView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Eski metot (geri uyumluluk için korundu)
    private void addTranslationCard(LinearLayout parentLayout, String originalText, String translatedText, String description) {
        addTranslationCard(parentLayout, originalText, translatedText, description, R.drawable.ic_dialect);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 