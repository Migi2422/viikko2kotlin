# Week 2 – Task Manager (Jetpack Compose + ViewModel)

Tällä viikolla laajennettiin Week1-projektia lisäämällä ViewModel-pohjainen tilanhallinta, erillinen HomeScreen-näkymä sekä tehtävien lisääminen, poistaminen, suodattaminen ja järjestäminen. Projekti on toteutettu Jetpack Compose -kirjastolla.

---

##  Tavoitteet

- Siirtää HomeScreen omaan tiedostoonsa
- Pitää MainActivity aloitusaktiviteettina
- Ottaa käyttöön TaskViewModel
- Siirtää tehtävälistan tilanhallinta ViewModeliin
- Näyttää tehtävät LazyColumn-komponentilla
- Toteuttaa:
  - uuden tehtävän lisääminen
  - tehtävän poistaminen
  - tehtävän done-tilan vaihtaminen
  - suodatus (näytä vain tehdyt)
  - järjestäminen eräpäivän mukaan
- Huolehtia, että UI päivittyy automaattisesti ViewModelin tilan muuttuessa

---


---

##  TaskViewModel

TaskViewModel hallitsee sovelluksen tilaa ja sisältää:

- `var tasks by mutableStateOf(listOf<Task>())`
- mock-datan alustuksen init-blokissa
- alkuperäisen listan tallennuksen suodatusta varten

### Funktiot

| Funktio | Kuvaus |
|--------|--------|
| `addTask(task)` | Lisää uuden tehtävän |
| `toggleDone(id)` | Vaihtaa tehtävän done-tilan |
| `removeTask(id)` | Poistaa tehtävän |
| `filterByDone(done)` | Suodattaa tehtävät tilan mukaan |
| `sortByDueDate()` | Järjestää tehtävät eräpäivän mukaan |
| `resetFilter()` | Palauttaa alkuperäisen listan |

---

##  HomeScreen

HomeScreen sisältää käyttöliittymän, joka:

- näyttää tehtävälistan LazyColumnilla
- näyttää jokaisella rivillä:
  - Checkbox (done-tila)
  - Tehtävän nimi ja eräpäivä
  - Delete-nappi
- sisältää TextFieldit:
  - uuden tehtävän nimelle
  - uuden tehtävän eräpäivälle
- sisältää napit:
  - Add task
  - Sort by due date
  - Show only done tasks
  - Show all tasks

UI käyttää ViewModelia näin:

```kotlin
val viewModel: TaskViewModel = viewModel()
setContent {
    Viikko1Theme {
        HomeScreen()
    }
}


