import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.farmingdale.pizzapartybottomnavbar.HungerLevel
import edu.farmingdale.pizzapartybottomnavbar.PizzaPartyViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaPartyScreen(
    drawerState: DrawerState, // DrawerState passed to control the drawer
    modifier: Modifier = Modifier,
    partyViewModel: PizzaPartyViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pizza Party") },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open() // Open the drawer when the menu icon is clicked
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = modifier.padding(innerPadding).padding(10.dp)
            ) {
                AppTitle(modifier)
                PartySize(
                    numPeopleInput = partyViewModel.numPeopleInput,
                    onValueChange = { partyViewModel.numPeopleInput = it },
                    modifier = modifier
                )
                HungerLevelSelection(
                    hungerLevel = partyViewModel.hungerLevel,
                    onSelected = { partyViewModel.hungerLevel = it },
                    modifier = modifier
                )
                TotalPizzas(
                    totalPizzas = partyViewModel.totalPizzas,
                    modifier = modifier
                )
                CalculateButton(
                    onClick = { partyViewModel.calculateNumPizzas() },
                    modifier = modifier
                )
            }
        }
    )
}

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Text(
        text = "Pizza Party",
        fontSize = 38.sp,
        modifier = modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun PartySize(
    numPeopleInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NumberField(
        labelText = "Number of people?",
        textInput = numPeopleInput,
        onValueChange = { onValueChange(it) },
        modifier = modifier.padding(bottom = 16.dp).fillMaxWidth()
    )
}

@Composable
fun HungerLevelSelection(
    hungerLevel: HungerLevel,
    onSelected: (HungerLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    val hungerItems = listOf("Light", "Medium", "Hungry", "Very hungry")

    RadioGroup(
        labelText = "How hungry?",
        radioOptions = hungerItems,
        selectedOption = when (hungerLevel) {
            HungerLevel.LIGHT -> hungerItems[0]
            HungerLevel.MEDIUM -> hungerItems[1]
            HungerLevel.HUNGRY -> hungerItems[2]
            HungerLevel.RAVENOUS -> hungerItems[3]
        },
        onSelected = {
            onSelected(when (it) {
                hungerItems[0] -> HungerLevel.LIGHT
                hungerItems[1] -> HungerLevel.MEDIUM
                hungerItems[2] -> HungerLevel.HUNGRY
                hungerItems[3] -> HungerLevel.RAVENOUS
                else -> HungerLevel.RAVENOUS
            })
        },
        modifier = modifier
    )
}

@Composable
fun TotalPizzas(
    totalPizzas: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Total pizzas: $totalPizzas",
        fontSize = 22.sp,
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp)
    )
}

@Composable
fun CalculateButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text("Calculate")
    }
}

@Composable
fun NumberField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textInput,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}

@Composable
fun RadioGroup(
    labelText: String,
    radioOptions: List<String>,
    selectedOption: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelectedOption: (String) -> Boolean = { selectedOption == it }

    Column {
        Text(labelText)
        radioOptions.forEach { option ->
            Row(
                modifier = modifier
                    .selectable(
                        selected = isSelectedOption(option),
                        onClick = { onSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedOption(option),
                    onClick = null,
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(
                    text = option,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}
