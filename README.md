# Mikailov_CFT
CFT test task

Ссылка на APK: https://disk.yandex.com/d/yQGIc44I08k-tw

Используемые библиотеки и причины выбора в их пользу:

Retrofit 2 обеспечивает:

  1) Простую интеграцию API: Retrofit 2 упрощает процесс интеграции API в приложение. Она обеспечивает простой и интуитивно понятный способ выполнения сетевых запросов и обработки ответов.
  2) Автоматический разбор JSON: Retrofit 2 может автоматически преобразовывать ответы JSON в объекты, что облегчает работу с данными, возвращаемыми с сервера.
  3) Конфигурирование: Retrofit 2 хорошо настраивается и позволяет конфигурировать такие аспекты HTTP-клиента, как таймаут соединения, таймаут ответа и многое другое.
  4) Интеграцию с OkHttp: Retrofit 2 может быть легко интегрирован с OkHttp, что облегчает добавление таких функций, как кэширование и аутентификация. Можно также создать кастомный интерсептор.


Hilt — это Dependency Injection библиотека обеспечивающая:

1) Упрощенную инъекцию зависимостей: Hilt упрощает предоставление и внедрение зависимостей в приложение Android с помощью аннотаций, сокращая количество кода, необходимого для внедрения зависимостей.
2) Скопинг: Hilt поддерживает различные варианты масштабирования, такие как application, activity, fragment и другие. Это позволяет легко управлять жизненным циклом зависимостей и избегать утечек памяти.
3) Генерируемый код: Hilt генерирует код, необходимый для внедрения зависимостей, во время компиляции, что позволяет ускорить запуск приложения и повысить его производительность.
4) Совместимость с другими DI-фреймворками: Hilt совместим с другими популярными DI-фреймворками, такими как Dagger, что позволяет легко перейти на Hilt.
5) Модулирование: Hilt поддерживает модулизацию, что позволяет легко создавать независимые модули, которые можно повторно использовать в различных частях приложения. 


Room — это популярная библиотека баз данных и обеспечивает:

1)	Упрощенное управление базой данных: Room упрощает управление базами данных, предоставляя уровень абстракции над SQLite, который является движком базы данных по умолчанию, используемым в Android. Room уменьшает количество кода, необходимого для создания и управления базой данных, упрощая и ускоряя работу с базами данных в приложении.
2)	Слой абстракции: Room предоставляет слой абстракции над SQLite, что означает, что в будущем можно легко перейти на другой движок базы данных без необходимости переписывать весь код.
3)	Интеграцию с LiveData: Room может быть легко интегрирован с LiveData, который представляет собой наблюдаемый держатель данных, уведомляющий своих наблюдателей об изменении данных. Это позволяет легко обновлять пользовательский интерфейс с помощью последних данных из базы данных.


LiveData - это data holder observable-данных, учитывающий жизненный цикл и обеспечивает:

1)	Автоматическое обновление UI: LiveData автоматически обновляет пользовательский интерфейс последними данными при изменении базового источника данных. Это устраняет необходимость в ручном обновлении и помогает синхронизировать пользовательский интерфейс с данными.
2)	Учитывание жизненного цикла: LiveData ориентирована на жизненный цикл, что означает, что она разработана для работы с жизненным циклом компонента приложения Android, такого как активность или фрагмент. Это позволяет легко избежать утечек памяти и других распространенных проблем, связанных с управлением жизненным циклом приложения Android.
3)	Согласованность данных: LiveData обеспечивает согласованность данных, гарантируя, что последние данные всегда доступны, даже когда активность или фрагмент уничтожается и создается заново.
4)	Интеграцию с ViewModel: LiveData может быть легко интегрирована с ViewModel, который представляет собой класс, предназначенный для хранения и управления данными, связанными с пользовательским интерфейсом, с учетом жизненного цикла. Эта интеграция помогает улучшить разделение задач и упрощает управление данными приложения.
5)	Сокращение объема кода: LiveData устраняет необходимость в кодовом шаблоне, предоставляя простой и интуитивно понятный способ наблюдения за изменениями данных. Это позволяет сосредоточиться на логике приложения, а не на управлении обновлениями данных.


Navigation Component - это библиотека, которая обеспечивает основу для навигации между пунктами назначения в приложении Android:

1)	Упрощенная навигация: Navigation Component упрощает навигацию приложения, предоставляя единый, последовательный способ перехода между пунктами назначения в приложении. Это облегчает управление navigation flow приложения и обеспечивает пользователям постоянный UX.
2)	Safe Args: Navigation Component включает в себя плагин Safe Args, который генерирует type-safe код для передачи аргументов между пунктами назначения. Это помогает устранить ошибки во время выполнения и гарантирует, что передаваемые данные имеют правильный тип..
3)	Совместимость: Navigation совместим с широким спектром библиотек и фреймворков Android, таких как ViewModel, LiveData и Data Binding. Это облегчает его использование с другими инструментами разработки Android.

Coroutines:

1)	Асинхронное программирование: Coroutines упрощает асинхронное программирование, позволяя разработчикам писать асинхронный код в синхронном стиле. Это облегчает чтение, написание и сопровождение асинхронного кода.
2)	Масштабируемость: Корутины позволяют писать масштабируемый, неблокирующий код, который может обрабатывать большое количество параллельных задач. Это помогает повысить производительность приложений, снизить потребление ресурсов и улучшить пользовательский опыт.
3)	Интеграция с другими библиотеками Android: Coroutines разработаны таким образом, чтобы хорошо работать с другими библиотеками и фреймворками Android, такими как LiveData, Room, Retrofit и другими. Это позволяет легко включать coroutines в существующие проекты и использовать преимущества других библиотек Android.
4)	Низкая нагрузка на память: Coroutines имеют низкие накладные расходы памяти и являются легковесными, что означает, что они потребляют меньше ресурсов, чем другие параллельные фреймворки.
5)	Простота изучения: Coroutines просты в освоении, особенно для разработчиков, имеющих опыт работы с синхронным программированием. Синтаксис лаконичен и легко читается.
