# Task Scheduler and Parallel Processing of Multimedia Data

The project aims to address the following key motivations:

- Optimizing Task Execution: By leveraging parallel processing capabilities, the task scheduler aims to improve the overall performance and efficiency of task execution. It allows for the distribution of tasks across multiple processor cores, enabling concurrent execution and reducing the overall processing time.

- Scalability: The project focuses on providing a scalable solution that can handle an increasing number of tasks efficiently. With the ability to leverage parallelism, the task scheduler can effectively distribute and manage tasks across available processor cores, ensuring optimal resource utilization.

- Multimedia Data Processing: The project targets the processing of multimedia data, which often involves computationally intensive tasks such as image and video processing, audio analysis, and more. By incorporating parallel processing techniques, the system aims to accelerate the processing of multimedia data, enabling faster and more efficient analysis and manipulation.

- API-Based Approach: The project adopts an API-based design, providing a well-defined programming interface for interacting with the task scheduler. This allows for easy integration and extensibility, enabling developers to utilize the task scheduler in their own applications or workflows.

- GUI Application: The project includes a logically separate GUI application that utilizes the defined API for task scheduling. The GUI application provides a user-friendly interface for managing tasks, configuring parallel processing settings, and monitoring the progress of task execution.

By developing a task scheduler with parallel processing capabilities and providing a user-friendly GUI application, the project aims to enhance task execution efficiency, scalability, and multimedia data processing capabilities. It offers a flexible and accessible solution for managing and processing tasks in various domains that involve computationally intensive operations.

## Project Motivation

The motivation behind the Task Scheduler and Parallel Processing of Multimedia Data project is to develop a task scheduler capable of exploiting parallelism at the level of processor cores. The goal is to create an efficient and scalable system for managing and processing tasks in an object-oriented programming language.

## Getting started

### Key Dependencies & Platforms

- [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html): Make sure you have Java 11 installed on your machine. You can download and install it from the official Oracle website or use a Java Development Kit (JDK) distribution suitable for your operating system.

- [Eclipse IDE](https://www.eclipse.org/ide/): I recommend using Eclipse IDE for Java development. Make sure you have Eclipse IDE installed on your machine. You can download it from the Eclipse website and follow the installation instructions.

## Key Features

### Key Features of Task Scheduler

- `Basic Functionality`: The task scheduler should properly implement the fundamental functionalities required for task scheduling.

- `Simple API`: The task scheduler should provide a straightforward API that allows users to perform task scheduling operations easily. The API should be user-friendly and intuitive.

- `Configurable Number of Processor Cores`: The task scheduler should allow users to specify the number of available processor cores for task execution. This feature enables the utilization of parallelism and efficient distribution of tasks across multiple cores.

- `Concurrent Task Execution`: The task scheduler should support concurrent execution of multiple tasks. Users should be able to specify the maximum number of tasks that can be executed simultaneously.

- `Task Execution Time and Deadline`: Users should be able to define the allowed total execution time for each task and set a deadline by which the task must be completed or terminated.

- `Parallelism Level Specification`: The task scheduler should enable users to specify the allowed level of parallelism for each task. This feature allows users to control the number of processor cores that can be utilized for a particular task.

- `Customizable API`: Users should have the flexibility to define their own API, which can be used in task implementation, to provide additional functionalities as required by the project's specifications.

- `Separate Process Execution`: Each task should be able to run as a separate process. This ensures isolation and proper management of resources for each task.

- `Unit Tests`: The task scheduler should include simple unit tests that demonstrate its functionality and validate its proper operation.

- `Priority Scheduling`: The task scheduler should support priority-based task scheduling, allowing users to set priorities for individual tasks. This feature ensures that tasks with higher priorities are executed first, if necessary.

By incorporating these key features, the task scheduler provides a comprehensive and efficient solution for managing and scheduling tasks, taking advantage of parallelism and allowing users to configure various aspects of task execution.

### Key Features of GUI application

- `Non-blocking Graphical User Interface`: The GUI application should have a non-blocking graphical user interface that allows users to interact with the application without experiencing delays or freezes. The application should provide a smooth and responsive user experience.

- `Easy Extension for New Task Types`: The GUI application should be designed in a way that allows for easy extension with new task types. Users should be able to easily add and configure different types of tasks within the application.

- `Flexible Task Scheduling Options`: The application should enable users to specify various scheduling options, including task priorities and prevention mechanisms. Users should have control over how tasks are prioritized and prevented from execution under specific conditions.

- `Customizable Task Properties`: Users should be able to specify all relevant properties of a task, such as the allowed execution time, deadlines, and other specific requirements. The application should provide a user-friendly interface for configuring these task properties.

- `Task Monitoring`: The GUI application should support real-time monitoring of tasks during their execution. Users should be able to view the progress and status of ongoing tasks, along with relevant details and logs.

- `Task Creation, Start, and Removal`: The application should allow users to create new tasks, initiate their execution, and remove tasks as needed. Users should have full control over the lifecycle of tasks within the application.

By incorporating these key features, the GUI application provides a user-friendly and flexible interface for managing task scheduling. Users can easily configure and monitor tasks, specify scheduling options, and interact with the application seamlessly, enhancing their overall experience and productivity.

### Key Features of The Multimedia Data Processing Task

- `Custom Task Type`: Define a task type specifically designed for non-trivial processing of multimedia files. The task should encapsulate a specific algorithm chosen during the course.

- `Support in GUI Application`: Provide support for working with tasks of the defined task type in the GUI application. Users should be able to create, configure, and manage tasks related to multimedia data processing within the application.

- `Task Execution Using the Scheduler`: The task should be compatible with the task scheduler's API, allowing it to be scheduled for execution using the scheduler's capabilities. The task should utilize the provided task API to interact with the task scheduler.

- `Parallel Processing of Multiple Media Files`: The task should be capable of parallel processing of a large number of input multimedia files. It should efficiently distribute the processing workload across available processor cores, leveraging parallelism to improve performance.

- `Accelerated Parallel Processing of a Single Media File`: The task should further support parallel processing of a single multimedia file, showcasing acceleration. The application should provide a means to measure the speedup achieved by the parallel processing approach, using a suitably sized multimedia file for testing.

By incorporating these key features, the multimedia data processing task offers efficient parallel processing capabilities for both multiple and individual multimedia files. Users can leverage the task type within the GUI application, benefitting from accelerated processing and the ability to schedule and manage tasks using the task scheduler's API.

## Setup

- Adding Images

Prepare your own images in PNG format that you want to process.
Copy the images to a designated folder on your local machine.

- Setting Image Input and Output Paths

Open the source code of the program and locate the section where the input and output paths are specified.
Update the path variable in the code to point to the folder where you placed your images. (that will be
prompted when start the program)

- Running the Program

Compile and run the program using your preferred Java development environment (e.g., Eclipse).
When prompted by the program, enter the path to the folder containing your input images.
Provide the desired output path where you want the resulting processed images to be saved.

- Algorithm Description

The algorithm employed in the program is as follows:

Constructing a new image by sorting pixels by column or type according to some criterion.
Parallel sorting is enabled to improve performance by utilizing multiple processor cores.
The program will process each input image using the algorithm and generate corresponding output images in the specified output folder.

By following these steps, you can add your own images to the designated folder, specify the input and output paths in the code, and run the program to process the images using the described algorithm. The program will save the processed images to the specified output folder.