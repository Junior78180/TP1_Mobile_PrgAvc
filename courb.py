import numpy as np
import matplotlib.pyplot as plt
from six import print_

# Function to read data from a file
def read_data(file_path):
    with open(file_path, 'r') as file:
        # Read lines and replace commas with dots
        data = [line.strip().replace(',', '.') for line in file if line.strip()]
    # Convert data to a NumPy array
    return np.array([list(map(float, line.split())) for line in data])

# Function to calculate speedup
def calculate_speedup(data):
    # Extract columns
    temps_execution = data[:, 3]  # temps_ms
    nombre_process = data[:, 2]    # nombre_process

    # Calculate T1 (median execution time for 1 process)
    T1 = np.median(temps_execution[nombre_process == 1])

    # Calculate Tp (median execution time for each number of processes)
    unique_processes = np.unique(nombre_process)
    Tp = []

    for p in unique_processes:
        Tp.append(np.median(temps_execution[nombre_process == p]))

    # Convert Tp to a NumPy array and replace zero values to avoid division by zero
    Tp = np.array(Tp)
    Tp[Tp == 0] = np.nan

    # Calculate Sp (speedup for each number of processes)
    Sp = T1 / Tp

    # Replace NaN values in Sp with zero
    Sp = np.nan_to_num(Sp, nan=0.0)

    return Sp, unique_processes

# Function to plot the graph
def plot_speedup(speedup_data, nombre_process_data, ntot_values):
    plt.figure(figsize=(10, 6))

    for speedup, nombre_process, ntot in zip(speedup_data, nombre_process_data, ntot_values):
        plt.plot(nombre_process, speedup, marker='o', label=f'ntot = {ntot}')

    plt.title('Speedup en fonction du nombre de processus')
    plt.xlabel('Nombre de processus')
    plt.ylabel('Speedup')
    plt.axhline(1, color='red', linestyle='--', label='Speedup = 1')

    # Diagonal line to represent ideal scalability
    max_process = max(max(nombre_process) for nombre_process in nombre_process_data)
    plt.plot([1, max_process], [1, max_process], color='blue', linestyle='--', label='Scalabilité idéale')

    plt.legend()
    plt.grid()

    plt.xlim(left=0)
    plt.ylim(bottom=0)  # To avoid negative values on the y-axis
    plt.gca().set_aspect('equal', adjustable='box')

    # Display all units on the axes
    x_ticks = np.arange(0, max_process + 1, 1)  # Adjust the interval as needed
    y_ticks = np.arange(0, max(max(speedup) for speedup in speedup_data) + 1, 1)  # Adjust the interval as needed
    plt.xticks(x_ticks)
    plt.yticks(y_ticks)

    plt.show()

# Main
if __name__ == "__main__":
    file_path = 'out_pi_Scala_Forte_salle_G26_4c.txt'  # Path to the data file

    # Read all data
    data = read_data(file_path)

    # Filter data to retrieve only those with 1 core (column 3, index 2)
    filtered_data_1_core = data[data[:, 2] == 1]  # Assuming the third column contains the number of cores

    ntot_values = np.unique(filtered_data_1_core[:, 1])

    # Initialize lists to store results
    speedup_data = []
    nombre_process_data = []

    # Calculate speedup for each dataset
    for ntot in ntot_values:
        # Filter data for the corresponding total number of darts
        filtered_data = data[data[:, 1] == ntot]

        # Calculate speedup for the filtered data
        speedup, nombre_process = calculate_speedup(filtered_data)

        # Add results to the list
        speedup_data.append(speedup)
        nombre_process_data.append(nombre_process)

    # Plot the curves for each total number of darts
    plot_speedup(speedup_data, nombre_process_data, ntot_values)