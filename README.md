# 22byte_test_task

## Задача: реализовать новостную ленту с помощью списка (recyclerView) на основе данных, получаемых через API.

### Выполненные задачи:
- Выбран подходящий дизайн (за основу взята лента с сервиса Яндекс новости)
- Реализована лента новостей с пагинацией при помощи Paging 3
- Отслеживание состояний списка (загрузка и ошибка)
- При нажатии на элемент списка просходит переход на экран с WebView где загружается страница оригинального источника

### Используемые технологии:
- Retrofit + OkHttp с кастомным Interceptor
- Kotlin coroutines, преимущественно Flow
- Clean Arhitecture
- Отдельные модели данных для каждого слоя
- Navigation Component + Safe Args
- Пагинация с помощью Paging 3 вместе с LoadAdapter
- Внедрение зависимостей через Hilt

### Скриншоты приложения
<img src="https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/22byte/22byte_first_screen.png" alt="J" width="400"/>
<img src="https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/22byte/22byte_second_screen.png" alt="J" width="400"/>
<img src="https://github.com/bogdanov2812/Screenshots/blob/master/Mobile_apps/22byte/22byte_third_screen.png" alt="J" width="400"/>
