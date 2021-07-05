package com.example.madcamp_week1;

public class Restaurants {
    public static String[] rest_images = new String[] {
            "https://mblogthumb-phinf.pstatic.net/MjAxOTA0MjVfMTE3/MDAxNTU2MTU5MzA4NjI5.8F-sqIKiu9lWrdJt5ErJPdOQMVe_l_qo1P-5aNbeU3wg._c-AKqyw8r-E9qJvjy8eMOSQyECdk3aFjLzkUB33EDog.JPEG.doli1573/IMG_1115.jpg?type=w800",
            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzA1MDhfMTcx%2FMDAxNDk0MjIxNzIyMjcx.vIJytvUfqBiBh_tBlUJx7ob1-0PtPVy1Idsga1BYcnUg.zNkE0Z3xYlkRX2Qb9Ut2ecVnYCWlX2sPZVWV9x_YvDAg.JPEG.crew_kw%2FIMG_1977.JPG&type=sc960_832",
            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA0MjlfMjQw%2FMDAxNjE5Njc1MDQyNDQ3.psEUY2oHxmPx7oNhqta-PSRmFuC4MAknJllmN6mZfO4g.VtsLylNRGKaRAdukVn3FaKaRSpPsj4f9ujyBT0-vXz4g.JPEG.qbdnjs35%2F24221F65-A30C-404F-B6DE-69D307FC9883.jpeg&type=sc960_832",
            "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fmyplace-phinf.pstatic.net%2F20210603_173%2F1622689397917IXqO1_JPEG%2Fupload_2b4cecc42020e0062dd616d84c79d6da.jpeg",
            "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fmyplace-phinf.pstatic.net%2F20210510_244%2F1620631658367leyhn_JPEG%2Fupload_72ca70f92ea54351c355a24bb5b28ec2.jpeg",
            "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA2MjZfNzIg%2FMDAxNjI0NjkwMDk2MTQy.T7JBiGkb3odVF5lX2rZeK2Pipc3WzTiGuxQmf0amUVQg.xPSUxVToolKoHRr2dwlQg9yp-wg5RdiO8Z2dqK39VBgg.JPEG.bebeisadog%2Foutput_3433242373.jpg",
            "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20170912_8%2F1505218998029G6WdR_JPEG%2F-jpZW5RzciRZfFrgst9zbMqM.jpg",
            "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTAxMjBfMjU2%2FMDAxNTQ3OTE5ODAxMDUz.PPX2l8LXCs4wKiXk5vBJyZRlgumgJgYXbOe0FDkezi8g.8DMWKau92PSxffN5_ITfC7sXpvoCJX0Z10mHleJYeF8g.JPEG.mrp%2FPC193342.JPG",
            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzAyMjdfMjA5%2FMDAxNDg4MTk1MzU1NDU1.RuH_ABacczoNVY2wa9MgJwTKE_XTvqxbQhMom65XKZcg.R8qvzxsdIM63ZN6jNd0Hud2GXLrEz0lszKdjYHr4PcYg.JPEG.botonssi%2FIMG_9337.JPG&type=sc960_832",
            "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fmyplace-phinf.pstatic.net%2F20210412_177%2F1618187731911YE1wG_JPEG%2Fupload_6cc00db088626b2a6ec4ebdbeedc1e8d.jpg",
            "https://drive.google.com/file/d/1iuLn-QmW5EKq3TuUATmSfvz3ZelR43hi/view?usp=sharing",
            "https://drive.google.com/file/d/1a9A3OSL1pw3IDp9p07bK4Hfr-8hI8UC4/view?usp=sharing",
            "https://drive.google.com/file/d/1iuLn-QmW5EKq3TuUATmSfvz3ZelR43hi/view?usp=sharing",
            "https://drive.google.com/file/d/1a9A3OSL1pw3IDp9p07bK4Hfr-8hI8UC4/view?usp=sharing",
            "https://drive.google.com/file/d/1iuLn-QmW5EKq3TuUATmSfvz3ZelR43hi/view?usp=sharing",
            "https://drive.google.com/file/d/1a9A3OSL1pw3IDp9p07bK4Hfr-8hI8UC4/view?usp=sharing",
            "https://drive.google.com/file/d/1iuLn-QmW5EKq3TuUATmSfvz3ZelR43hi/view?usp=sharing",
            "https://drive.google.com/file/d/1a9A3OSL1pw3IDp9p07bK4Hfr-8hI8UC4/view?usp=sharing",
            "https://drive.google.com/file/d/1iuLn-QmW5EKq3TuUATmSfvz3ZelR43hi/view?usp=sharing",
            "https://drive.google.com/file/d/1a9A3OSL1pw3IDp9p07bK4Hfr-8hI8UC4/view?usp=sharing",
            "https://drive.google.com/file/d/1iuLn-QmW5EKq3TuUATmSfvz3ZelR43hi/view?usp=sharing",
            "https://drive.google.com/file/d/1a9A3OSL1pw3IDp9p07bK4Hfr-8hI8UC4/view?usp=sharing",
    };


}
