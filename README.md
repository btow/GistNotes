# GistNotes
Записи сервиса GitHub Gists с заметками

Цель
Продемонстрировать навыки разработки для мобильных устройств.
Задача
Приложение для создания заметок к гистам: https://gist.github.com, https://gist.github.com/discover
Документация на API: https://developer.github.com/v3/gists/
Мобильное приложение
Приложение должно работать на версиях Android 2.2 и выше (Ios 7 и выше).
Код приложения должен быть выложен в репозиторий на GitHub.
Интерфейс
Заглавный экран показывает три кнопки: "All Gists", "Notes", "All in one".
Первые две кнопки ведут на соответствующие списки. 
Третья, "All in one" — на экран с двумя табами: "All Gists", "Notes", которые показывают эти списки.
Список "All Gists"
загрузить список публичных гистов (см. API на GET /gists/public)
загруженные гисты сохраняются локально (например, с помощью coredata) 
список обновляется с помощью стандартного pull-to-refresh
из списка можно перейти в детальное описание гиста
Экран детального описания
автор (owner: login, avatar)
название (description) (редактируемое поле)
на этом экране можно редактировать поле "название"
на этом экране можно добавлять заметку к гисту
все изменения сохраняются локально
из отредактированного гиста можно перейти к просмотру оригинального гиста
Список "Notes"
список гистов, которым добавили локальные заметки или изменили название
также можно перейти к детальному описанию
Общее
После редактирования гиста изменения должны отобразится в локальных списках (на устройстве).
