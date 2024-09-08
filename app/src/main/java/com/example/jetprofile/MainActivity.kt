package com.example.jetprofile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetprofile.components.CompanySection
import com.example.jetprofile.components.DetailSection
import com.example.jetprofile.ui.theme.JetProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetProfileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    // カラムで縦方向にUIを並べる
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp) // カラム全体に20dpのpadding
            .verticalScroll(rememberScrollState()) // 画面スクロール可能にする
    ) {
        // プロフィール画像
        Image(
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = "プロフィール",
            modifier = Modifier
                .size(100.dp)  // 縦横サイズ
                .clip(RoundedCornerShape(10.dp))  // 丸角
        )
        Spacer(modifier = Modifier.height(20.dp)) // スペーサーで間隔をあけてる

        // 名前
        Text(
            text = "Ryos",
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(20.dp))

        // 職業
        Text(
            text = "職業: Androidエンジニア",
            color = Color.Gray,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))

        CompanySection()

        Spacer(modifier = Modifier.height(20.dp))

        // mutableStateOfを使って、状態が変更された際にUI（Composable）が自動的に再描画されるようにする
        // 通常の変数宣言だと、onClickで値が変更されてもUIが再描画されないため反応しない
        // rememberを使うことで、Composeがリコンポーズされても変数が保持され、再初期化されるのを防ぐ
        var isShowDetail by remember { mutableStateOf(false) }

        // 詳細表示ボタン
        // 文法: Buttonの最後の引数はcontentというラムダ式であり、
        // 最後の引数がラムダ式の時kotlinでは、かっこの外にかける
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF85F6A)),
            onClick = { isShowDetail = !isShowDetail },
        ) {
            Text(text = "詳細を表示", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (isShowDetail) {
            DetailSection()
        }
    }
}


