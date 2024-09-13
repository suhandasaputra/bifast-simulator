<%-- 
    Document   : dashboard
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <%@include file='defaultextend.jsp'%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>MIOS</title>
        <style>
             body{
                font-size: 10px; 
            }
            .card {
                background-color: white;
            }
            .add_category {                
                display: inline-block;

            }

            th {
                background-color: #CCC;
            }


            #table_user th {
                /*font-size: 12px;*/
                background-color: #ACACAC;
                color: white;
                font-weight: 100;
            }
            #table_user td {
                /*font-size: 12px;*/
            }
        </style>
        <script>
            $(document).ready(function () {
                var table = $('#table_user').DataTable({
                    "ajax": {
                        "url": "/bifast_simulator/uss",
                        "type": "GET",
                        "dataSrc": "",
                        "contentType": "application/json"
                    },
                    "columns": [
                        {data: "regis_id"},
                        {data: "alias"},
                        {data: "alias_type"},
                        {data: "display_name"},
                        {data: "name"},
                        {data: "bank"},
                        {data: "bank_account"},
                        {data: "id_type"},
                        {data: "num_id"},
                        {data: "regis_status"},
                        {data: "date_create"}
                    ],
                    dom: 'Bfrtip',
                    buttons: [
                        {
                            extend: 'collection',
                            text: 'Export',
                            buttons:
                                    [
                                        {
                                            extend: "copyHtml5",
                                            title: "List Agent",
                                            exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                            footer: true
                                        },
                                        {
                                            extend: "csvHtml5",
                                            title: "List Agent",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "excelHtml5",
                                            title: "List Agent",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "pdfHtml5",
                                            title: "List Agent",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "print",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        }
                                    ]
                        }
                    ]
                });
            });
        </script>
    </head>
    <body class="hold-transition sidebar-mini">
        <div class="wrapper">
            <!-- Navbar -->
            <%@include file='header.jsp'%>
            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <%@include file='sidebar_left.jsp'%>
            <!--end sidebar-->
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <div class="content-header">
                    <section class="content">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <p class="text-left" style="color: #29B19C; font-size: 15px;">
                                                        List Agent
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="tab-content" style="width: 100%">
                                                    <div id="category" class="tab-pane fade in active show">
                                                        <div class="container">        
                                                            <table class="table" id="table_user">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Regis ID</th>
                                                                        <th>Alias</th>
                                                                        <th>Alias Type</th>
                                                                        <th>Display Name</th>
                                                                        <th>Name</th>                                                                        
                                                                        <th>Bank</th>
                                                                        <th>Account Number</th>
                                                                        <th>Id Type</th>
                                                                        <th>Number ID</th>
                                                                        <th>Status</th>
                                                                        <th>Date Create</th>
                                                                    </tr>
                                                                </thead>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>    
                                            </div>
                                            <!-- /.row -->
                                        </div>
                                        <!-- ./card-body -->
                                    </div>
                                    <!-- /.card -->
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                    </section>
                </div>
            </div>
            <!-- /.content-wrapper -->
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- Main Footer -->
            <%@include file='footer.jsp'%>
        </div>
    </body>

</html>
