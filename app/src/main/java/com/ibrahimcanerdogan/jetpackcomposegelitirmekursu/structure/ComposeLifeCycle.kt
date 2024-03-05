package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.structure

/*
* Yaşam döngüsü uygulama sayfasının çalışmasını takip ettiğimiz methodlardır.
* LaunchEffect, SideEffect, DisposableEffect olmak üzere 3 farklı çeşidi bulunmaktadır.
*
* LaunchedEffect: Sayfa HER görüntülendiğinde (Uygulama sayfası ilk kez açıldığında veya başka
* sayfadaban geri geldiğinde) BİR kez çalışır.
*
* SideEffect: Sayfa görüntülendiğinde (Uygulama sayfası ilk kez açıldığında veya başka sayfadan
* geri geldiğinde) veya arayüz state ile yenilendiğinde çalışır.
* Örneğin butona basılıdğında sayaç artıyorsa state yenilendiği için çağırılacaktır.
*
* DisposableEffect: Sayfadan ayrıldığımızda çalışır.
*
* */