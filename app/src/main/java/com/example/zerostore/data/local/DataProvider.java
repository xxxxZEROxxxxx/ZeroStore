package com.example.zerostore.data.local;
import com.example.zerostore.R;
import com.example.zerostore.data.model.*;
import com.example.zerostore.data.local.*;
import com.example.zerostore.ui.adapters.*;
import com.example.zerostore.ui.main.*;
import com.example.zerostore.ui.products.*;
import com.example.zerostore.ui.search.*;
import com.example.zerostore.ui.favorites.*;
import com.example.zerostore.ui.common.*;

import java.util.ArrayList;

public class DataProvider {

        // Category IDs
        public static final int CAT_AI = 1;
        public static final int CAT_GAMING = 2;
        public static final int CAT_STREAMING = 3;
        public static final int CAT_DESIGN = 4;
        public static final int CAT_GIFT_CARDS = 5;
        public static final int CAT_SOFTWARE = 6;
        public static final int CAT_ESIM = 7;
        public static final int CAT_MOBILE = 8;

        public static ArrayList<Category> getCategories() {
                ArrayList<Category> list = new ArrayList<>();
                list.add(new Category(CAT_AI, "اشتراكات الذكاء الاصطناعي", "🔥"));
                list.add(new Category(CAT_GAMING, "الألعاب", "🎮"));
                list.add(new Category(CAT_STREAMING, "البث والمشاهدة", "🎬"));
                list.add(new Category(CAT_DESIGN, "التصميم والمونتاج", "💼"));
                list.add(new Category(CAT_GIFT_CARDS, "بطاقات الهدايا", "💳"));
                list.add(new Category(CAT_SOFTWARE, "البرامج والسوشال ميديا", "🧩"));
                list.add(new Category(CAT_ESIM, "شرائح إلكترونية eSIM", "📡"));
                list.add(new Category(CAT_MOBILE, "حزم جوال", "📞"));
                return list;
        }

        public static ArrayList<Product> getProducts() {
                ArrayList<Product> list = new ArrayList<>();
                int id = 1;

                // =====================================================
                // AI Subscriptions (CAT_AI)
                // =====================================================
                list.add(new Product(id++, CAT_AI, "اشتراك ChatGPT Plus (شهر)",
                                "24", "شيكل", "شهر واحد", "1-24 ساعة", "ضمان كامل المدة",
                                "على ايميلك الشخصي أو حساب من عنا كامل الصلاحيات",
                                "اشتراك ChatGPT Plus الشهري يمنحك وصولاً لـ GPT-5.2 النسخة الأحدث والأسرع من OpenAI مع أولوية في الاستجابة وميزات متقدمة. أداء مثالي لصناع المحتوى والطلاب والمهنيين.",
                                "", true, true));

                list.add(new Product(id++, CAT_AI, "اشتراك Gemini AI Pro (سنة كاملة)",
                                "40", "شيكل", "سنة كاملة", "1-24 ساعة", "ضمان كامل المدة",
                                "تفعيل على إيميلك الشخصي أو إيميل خاص فيك من عنا",
                                "اشتراك Gemini AI Pro لمدة سنة كاملة. يشمل مساحة تخزين 2 تيرا بايت، ميزة إنشاء فيديوهات Vo3 (1000 كريدت/شهر)، وتقنية Nano Banana للصور.",
                                "", true, true));

                list.add(new Product(id++, CAT_AI, "اشتراك Perplexity AI Pro (سنة كاملة)",
                                "50", "شيكل", "سنة كاملة", "1-24 ساعة", "ضمان كامل المدة",
                                "إيميل حساب جديد (للحسابات الجديدة فقط)",
                                "اشتراك Perplexity Pro لمدة سنة كاملة بدلاً من 200$. بحث ذكي بالذكاء الاصطناعي مع مصادر موثوقة وإجابات دقيقة.",
                                "متاح فقط للحسابات الجديدة", true, true));

                list.add(new Product(id++, CAT_AI, "اشتراك Super Grok (شهر)",
                                "60", "شيكل", "شهر واحد", "1-24 ساعة", "ضمان كامل المدة",
                                "حساب خاص كامل البيانات (تستلم الإيميل والباسورد وتقدر تغير معلوماته)",
                                "حساب Super Grok خاص بدل السعر الرسمي 30$. يشمل بحث لحظي Real-time مع وصول لجميع بيانات وترندات X مباشرة، توليد صور احترافية داخل الشات، واستخدام موديل Grok 2 بدون قيود.",
                                "حساب خاص - ليس مشترك", true, false));

                list.add(new Product(id++, CAT_AI, "اشتراك Claude Pro (شهر)",
                                "50", "شيكل", "شهر واحد", "1-24 ساعة", "ضمان 30 يوم",
                                "إيميل الحساب",
                                "اشتراك Claude Pro يمنحك وصولاً لنموذج Claude المتقدم من Anthropic مع استخدام غير محدود.",
                                "", false, false));

                // =====================================================
                // Gaming (CAT_GAMING)
                // =====================================================
                list.add(new Product(id++, CAT_GAMING, "شحن شدات ببجي UC - 325 شدة",
                                "16", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في PUBG Mobile",
                                "325 شدة (UC) للعبة PUBG Mobile يتم شحنها مباشرة لحسابك.",
                                "", true, false));

                list.add(new Product(id++, CAT_GAMING, "شحن شدات ببجي UC - 660 شدة",
                                "30", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في PUBG Mobile",
                                "660 شدة (UC) للعبة PUBG Mobile يتم شحنها مباشرة لحسابك.",
                                "", true, true));

                list.add(new Product(id++, CAT_GAMING, "شحن شدات ببجي UC - 1800 شدة",
                                "75", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في PUBG Mobile",
                                "1800 شدة (UC) للعبة PUBG Mobile يتم شحنها مباشرة لحسابك.",
                                "", false, false));

                list.add(new Product(id++, CAT_GAMING, "شحن شدات ببجي UC - 3850 شدة",
                                "145", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في PUBG Mobile",
                                "3850 شدة (UC) للعبة PUBG Mobile يتم شحنها مباشرة لحسابك.",
                                "", false, false));

                list.add(new Product(id++, CAT_GAMING, "شحن شدات ببجي UC - 8100 شدة",
                                "285", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في PUBG Mobile",
                                "8100 شدة (UC) للعبة PUBG Mobile يتم شحنها مباشرة لحسابك.",
                                "", false, false));

                list.add(new Product(id++, CAT_GAMING, "شحن جواهر فري فاير Diamonds - 100",
                                "10", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في Free Fire",
                                "100 جوهرة (Diamond) للعبة Free Fire يتم شحنها مباشرة.",
                                "", false, false));

                list.add(new Product(id++, CAT_GAMING, "شحن جواهر فري فاير Diamonds - 310",
                                "20", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في Free Fire",
                                "310 جوهرة (Diamond) للعبة Free Fire يتم شحنها مباشرة.",
                                "", false, false));

                list.add(new Product(id++, CAT_GAMING, "شحن جواهر فري فاير Diamonds - 520",
                                "35", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "ID اللاعب في Free Fire",
                                "520 جوهرة (Diamond) للعبة Free Fire يتم شحنها مباشرة.",
                                "", false, false));

                // =====================================================
                // Streaming (CAT_STREAMING)
                // =====================================================
                list.add(new Product(id++, CAT_STREAMING, "اشتراك نتفلكس Netflix (شهر)",
                                "30", "شيكل", "شهر واحد", "1-6 ساعات", "ضمان 30 يوم",
                                "لا يتطلب شيء - حساب خاص جاهز",
                                "اشتراك Netflix شهري على حساب خاص بجودة عالية ومشاهدة غير محدودة.",
                                "الحساب مشترك - لا تغيّر الباسورد", true, false));

                list.add(new Product(id++, CAT_STREAMING, "شاهد VIP (شهر)",
                                "12", "شيكل", "شهر واحد", "1-6 ساعات", "ضمان 30 يوم",
                                "لا يتطلب شيء - حساب خاص جاهز",
                                "اشتراك Shahid VIP الشهري لمشاهدة المسلسلات والأفلام العربية والعالمية بجودة عالية.",
                                "", true, true));

                list.add(new Product(id++, CAT_STREAMING, "شاهد VIP (سنة)",
                                "90", "شيكل", "سنة كاملة", "1-6 ساعات", "ضمان كامل المدة",
                                "لا يتطلب شيء - حساب خاص جاهز",
                                "اشتراك Shahid VIP السنوي لمشاهدة المسلسلات والأفلام العربية والعالمية بجودة عالية طوال السنة.",
                                "", false, true));

                list.add(new Product(id++, CAT_STREAMING, "Disney+ (شهر)",
                                "20", "شيكل", "شهر واحد", "1-6 ساعات", "ضمان 30 يوم",
                                "لا يتطلب شيء - حساب خاص جاهز",
                                "اشتراك Disney+ الشهري لمشاهدة أفلام ومسلسلات Disney و Marvel و Star Wars.",
                                "", false, false));

                // =====================================================
                // Design & Editing (CAT_DESIGN)
                // =====================================================
                list.add(new Product(id++, CAT_DESIGN, "اشتراك كانفا برو Canva Pro (شهر)",
                                "15", "شيكل", "شهر واحد", "1-12 ساعة", "ضمان 30 يوم",
                                "إيميل حسابك في Canva",
                                "اشتراك Canva Pro يفتح لك جميع القوالب والعناصر والأدوات الاحترافية للتصميم.",
                                "", true, false));

                list.add(new Product(id++, CAT_DESIGN, "اشتراك ادوبي Adobe Creative Cloud (شهر)",
                                "25", "شيكل", "شهر واحد", "1-24 ساعة", "ضمان 30 يوم",
                                "حساب Adobe الخاص بك",
                                "اشتراك Adobe Creative Cloud الشهري يشمل Photoshop, Illustrator, Premiere Pro والمزيد.",
                                "", true, false));

                list.add(new Product(id++, CAT_DESIGN, "اشتراك كاب كت برو CapCut Pro (شهر)",
                                "15", "شيكل", "شهر واحد", "تسليم فوري", "ضمان كامل المدة",
                                "على إيميلك الشخصي أو حساب خاص من عنا",
                                "اشتراك CapCut Pro شهر كامل. حساب خاص بملكية كاملة - تقدر تغير الإيميل وكلمة السر فوراً. خصوصية تامة، إزالة الخلفية، تصدير 4K، وبدون علامة مائية.",
                                "حساب خاص غير مشترك مع أحد", true, true));

                // =====================================================
                // Gift Cards (CAT_GIFT_CARDS)
                // =====================================================
                list.add(new Product(id++, CAT_GIFT_CARDS, "بطاقة ابل ستور Apple Gift Card $10",
                                "42", "شيكل", "فوري", "دقائق", "ضمان الكود",
                                "لا يتطلب شيء",
                                "بطاقة هدايا Apple بقيمة 10 دولار، تستخدم لشراء التطبيقات والألعاب والاشتراكات من App Store.",
                                "الكود صالح للمتجر الأمريكي فقط", false, false));

                list.add(new Product(id++, CAT_GIFT_CARDS, "بطاقة ابل ستور Apple Gift Card $25",
                                "100", "شيكل", "فوري", "دقائق", "ضمان الكود",
                                "لا يتطلب شيء",
                                "بطاقة هدايا Apple بقيمة 25 دولار، تستخدم لشراء التطبيقات والألعاب والاشتراكات من App Store.",
                                "الكود صالح للمتجر الأمريكي فقط", true, false));

                list.add(new Product(id++, CAT_GIFT_CARDS, "بطاقة ابل ستور Apple Gift Card $50",
                                "195", "شيكل", "فوري", "دقائق", "ضمان الكود",
                                "لا يتطلب شيء",
                                "بطاقة هدايا Apple بقيمة 50 دولار، تستخدم لشراء التطبيقات والألعاب والاشتراكات من App Store.",
                                "الكود صالح للمتجر الأمريكي فقط", false, false));

                // =====================================================
                // Software & Social Media (CAT_SOFTWARE)
                // =====================================================

                // -- Telegram Premium --
                list.add(new Product(id++, CAT_SOFTWARE, "اشتراك تليجرام بريميوم Telegram Premium (3 أشهر)",
                                "55", "شيكل", "3 أشهر", "تفعيل فوري", "ضمان كامل المدة",
                                "يوزرنيم تيليجرام الخاص بك",
                                "اشتراك Telegram Premium لمدة 3 أشهر. ميزات حصرية مثل رفع ملفات كبيرة وستيكرات مميزة وبدون إعلانات.",
                                "", true, true));

                list.add(new Product(id++, CAT_SOFTWARE, "اشتراك تليجرام بريميوم Telegram Premium (6 أشهر)",
                                "70", "شيكل", "6 أشهر", "تفعيل فوري", "ضمان كامل المدة",
                                "يوزرنيم تيليجرام الخاص بك",
                                "اشتراك Telegram Premium لمدة 6 أشهر. ميزات حصرية مثل رفع ملفات كبيرة وستيكرات مميزة وبدون إعلانات.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "اشتراك تليجرام بريميوم Telegram Premium (سنة كاملة)",
                                "115", "شيكل", "سنة كاملة", "تفعيل فوري", "ضمان كامل المدة",
                                "يوزرنيم تيليجرام الخاص بك",
                                "اشتراك Telegram Premium لمدة سنة كاملة. ميزات حصرية مثل رفع ملفات كبيرة وستيكرات مميزة وبدون إعلانات.",
                                "", false, true));

                // -- Telegram Stars --
                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 50 ⭐",
                                "5", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "50 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 100 ⭐",
                                "10", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "100 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 150 ⭐",
                                "15", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "150 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 250 ⭐",
                                "20", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "250 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 350 ⭐",
                                "26", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "350 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 750 ⭐",
                                "50", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "750 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 1000 ⭐",
                                "65", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "1000 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", true, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 1500 ⭐",
                                "90", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "1500 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 2500 ⭐",
                                "140", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "2500 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "نجوم تيليجرام - 5000 ⭐",
                                "280", "شيكل", "فوري", "تفعيل فوري", "ضمان كامل",
                                "يوزرنيم تيليجرام الخاص بك",
                                "5000 نجمة تيليجرام لاستخدامها في شراء المحتوى الرقمي والبوتات داخل تيليجرام.",
                                "", false, false));

                // -- TikTok Coins --
                list.add(new Product(id++, CAT_SOFTWARE, "عملات تيك توك - 150",
                                "10", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "يوزرنيم تيك توك الخاص بك",
                                "150 عملة TikTok لاستخدامها في إرسال الهدايا للمبدعين في البث المباشر.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "عملات تيك توك - 250",
                                "15", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "يوزرنيم تيك توك الخاص بك",
                                "250 عملة TikTok لاستخدامها في إرسال الهدايا للمبدعين في البث المباشر.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "عملات تيك توك - 500",
                                "30", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "يوزرنيم تيك توك الخاص بك",
                                "500 عملة TikTok لاستخدامها في إرسال الهدايا للمبدعين في البث المباشر.",
                                "", true, false));

                list.add(new Product(id++, CAT_SOFTWARE, "عملات تيك توك - 1000",
                                "55", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "يوزرنيم تيك توك الخاص بك",
                                "1000 عملة TikTok لاستخدامها في إرسال الهدايا للمبدعين في البث المباشر.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "عملات تيك توك - 2000",
                                "100", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "يوزرنيم تيك توك الخاص بك",
                                "2000 عملة TikTok لاستخدامها في إرسال الهدايا للمبدعين في البث المباشر.",
                                "", false, false));

                // -- Snapchat Plus --
                list.add(new Product(id++, CAT_SOFTWARE, "اشتراك سناب شات بلس (3 أشهر)",
                                "45", "شيكل", "3 أشهر", "تفعيل فوري", "ضمان كامل المدة",
                                "يوزرنيم سناب شات الخاص بك",
                                "اشتراك سناب شات بلس لمدة 3 أشهر مع ميزات حصرية ومميزة.",
                                "", true, true));

                list.add(new Product(id++, CAT_SOFTWARE, "اشتراك سناب شات بلس (6 أشهر)",
                                "85", "شيكل", "6 أشهر", "تفعيل فوري", "ضمان كامل المدة",
                                "يوزرنيم سناب شات الخاص بك",
                                "اشتراك سناب شات بلس لمدة 6 أشهر مع ميزات حصرية ومميزة.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "اشتراك سناب شات بلس (سنة كاملة)",
                                "130", "شيكل", "سنة كاملة", "تفعيل فوري", "ضمان كامل المدة",
                                "يوزرنيم سناب شات الخاص بك",
                                "اشتراك سناب شات بلس لمدة سنة كاملة مع ميزات حصرية ومميزة.",
                                "", false, true));

                // -- Gmail & Other Services --
                list.add(new Product(id++, CAT_SOFTWARE, "حساب Gmail جاهز",
                                "10", "شيكل", "فوري", "دقائق", "ضمان كامل",
                                "لا يتطلب شيء",
                                "حساب Gmail جاهز ببريد وباسورد لحل مشكلة إنشاء حساب جديد.",
                                "", false, false));

                list.add(new Product(id++, CAT_SOFTWARE, "حل مشكلة تسجيل الدخول تيليجرام",
                                "15", "شيكل", "فوري", "دقائق - ساعات", "ضمان كامل",
                                "رقم الهاتف المرتبط بحساب تيليجرام",
                                "حل مشكلة تسجيل الدخول في تيليجرام واستعادة الحساب.",
                                "", false, false));

                // =====================================================
                // eSIM - شرائح إلكترونية (CAT_ESIM)
                // =====================================================

                // -- Cellcom --
                list.add(new Product(id++, CAT_ESIM, "شريحة Cellcom - 400 جيجا (شهر)",
                                "65", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Cellcom سيلكوم بسعة 400 جيجا إنترنت لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", true, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة Cellcom - 500 جيجا 5G (شهر)",
                                "75", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Cellcom سيلكوم بسعة 500 جيجا إنترنت 5G لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                // -- Wecom --
                list.add(new Product(id++, CAT_ESIM, "شريحة Wecom - 500 جيجا (شهر)",
                                "53", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Wecom (تغطية سيلكوم) بسعة 500 جيجا إنترنت لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", true, true));

                list.add(new Product(id++, CAT_ESIM, "شريحة Wecom - 1500 جيجا (3 أشهر)",
                                "120", "شيكل", "3 أشهر", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Wecom (تغطية سيلكوم) بسعة 1500 جيجا إنترنت لمدة 3 أشهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, true));

                // -- 019 5G --
                list.add(new Product(id++, CAT_ESIM, "شريحة 019 5G - 100 جيجا (شهر)",
                                "75", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية 019 5G (تغطية بارتنر) بسعة 100 جيجا إنترنت 5G لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة 019 5G - 200 جيجا (شهر)",
                                "85", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية 019 5G (تغطية بارتنر) بسعة 200 جيجا إنترنت 5G لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                // -- Prime Mobile ALOHA --
                list.add(new Product(id++, CAT_ESIM, "شريحة Prime Mobile - 150 جيجا (شهر)",
                                "65", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Prime Mobile ALOHA (تغطية بليفون + هوت موبايل) بسعة 150 جيجا إنترنت لمدة شهر. ما بيصير عليها تبليك.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة Prime Mobile - 450 جيجا (3 أشهر)",
                                "149", "شيكل", "3 أشهر", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Prime Mobile ALOHA (تغطية بليفون + هوت موبايل) بسعة 450 جيجا إنترنت لمدة 3 أشهر. ما بيصير عليها تبليك.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                // -- Pelephone --
                list.add(new Product(id++, CAT_ESIM, "شريحة Pelephone - 150 جيجا (شهر)",
                                "59", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Pelephone بيلفون بسعة 150 جيجا إنترنت لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة Pelephone - 500 جيجا 5G (شهر)",
                                "79", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية Pelephone بيلفون بسعة 500 جيجا إنترنت 5G لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                // -- HotMobile --
                list.add(new Product(id++, CAT_ESIM, "شريحة HotMobile - 20 جيجا (شهر)",
                                "45", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية HotMobile هوت موبايل بسعة 20 جيجا إنترنت لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة HotMobile - 150 جيجا (شهر)",
                                "59", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية HotMobile هوت موبايل بسعة 150 جيجا إنترنت لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة HotMobile - 200 جيجا 5G (شهر)",
                                "65", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية HotMobile هوت موبايل بسعة 200 جيجا إنترنت 5G لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة HotMobile - 300 جيجا 5G (شهر)",
                                "75", "شيكل", "شهر واحد", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية HotMobile هوت موبايل بسعة 300 جيجا إنترنت 5G لمدة شهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", true, false));

                list.add(new Product(id++, CAT_ESIM, "شريحة HotMobile - 450 جيجا (3 أشهر)",
                                "125", "شيكل", "3 أشهر", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية HotMobile هوت موبايل بسعة 450 جيجا إنترنت لمدة 3 أشهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, true));

                list.add(new Product(id++, CAT_ESIM, "شريحة HotMobile - 900 جيجا (3 أشهر)",
                                "159", "شيكل", "3 أشهر", "دقائق", "ضمان كامل المدة",
                                "رقم الهاتف فقط",
                                "شريحة إلكترونية HotMobile هوت موبايل بسعة 900 جيجا إنترنت لمدة 3 أشهر.",
                                "يوجد رابط تحديث أونلاين وأوفلاين", false, false));

                // =====================================================
                // حزم جوال (CAT_MOBILE)
                // =====================================================
                list.add(new Product(id++, CAT_MOBILE, "حزمة إنترنت واتساب وتيليجرام",
                                "5", "شيكل", "حسب الباقة", "فوري", "ضمان كامل",
                                "رقم الهاتف فقط",
                                "تفعيل حزمة إنترنت واتساب وتيليجرام.",
                                "الدفع عبر التطبيق البنكي", false, false));

                list.add(new Product(id++, CAT_MOBILE, "1000 رسالة",
                                "10", "شيكل", "حسب الباقة", "فوري", "ضمان كامل",
                                "رقم الهاتف فقط",
                                "تفعيل حزمة 1000 رسالة.",
                                "الدفع عبر التطبيق البنكي", false, false));

                list.add(new Product(id++, CAT_MOBILE, "150 دقيقة + 150 رسالة",
                                "14", "شيكل", "حسب الباقة", "فوري", "ضمان كامل",
                                "رقم الهاتف فقط",
                                "تفعيل حزمة 150 دقيقة و 150 رسالة.",
                                "الدفع عبر التطبيق البنكي", true, false));

                list.add(new Product(id++, CAT_MOBILE, "600 دقيقة + 600 رسالة",
                                "30", "شيكل", "حسب الباقة", "فوري", "ضمان كامل",
                                "رقم الهاتف فقط",
                                "تفعيل حزمة 600 دقيقة و 600 رسالة.",
                                "الدفع عبر التطبيق البنكي", true, true));

                list.add(new Product(id++, CAT_MOBILE, "700 دقيقة + 700 رسالة",
                                "33", "شيكل", "حسب الباقة", "فوري", "ضمان كامل",
                                "رقم الهاتف فقط",
                                "تفعيل حزمة 700 دقيقة و 700 رسالة.",
                                "الدفع عبر التطبيق البنكي", false, false));

                list.add(new Product(id++, CAT_MOBILE, "750 دقيقة + 750 رسالة + 1.5 جيجا",
                                "44", "شيكل", "حسب الباقة", "فوري", "ضمان كامل",
                                "رقم الهاتف فقط",
                                "تفعيل حزمة 750 دقيقة و 750 رسالة و 1.5 جيجا إنترنت.",
                                "الدفع عبر التطبيق البنكي", false, false));

                return list;
        }

        public static ArrayList<Product> getProductsByCategory(int categoryId) {
                ArrayList<Product> filtered = new ArrayList<>();
                for (Product p : getProducts()) {
                        if (p.getCategoryId() == categoryId) {
                                filtered.add(p);
                        }
                }
                return filtered;
        }

        public static ArrayList<Product> getTopProducts() {
                ArrayList<Product> filtered = new ArrayList<>();
                for (Product p : getProducts()) {
                        if (p.isTop()) {
                                filtered.add(p);
                        }
                }
                return filtered;
        }

        public static ArrayList<Product> getOfferProducts() {
                ArrayList<Product> filtered = new ArrayList<>();
                for (Product p : getProducts()) {
                        if (p.isOffer()) {
                                filtered.add(p);
                        }
                }
                return filtered;
        }

        public static Product getProductById(int productId) {
                for (Product p : getProducts()) {
                        if (p.getId() == productId) {
                                return p;
                        }
                }
                return null;
        }

        public static Category getCategoryById(int categoryId) {
                for (Category c : getCategories()) {
                        if (c.getId() == categoryId) {
                                return c;
                        }
                }
                return null;
        }

        public static String getCategoryName(int categoryId) {
                Category cat = getCategoryById(categoryId);
                return cat != null ? cat.getNameAr() : "غير محدد";
        }
}

