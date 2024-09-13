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

            #table_detail th {
                background-color: #ACACAC;
                color: white;
                font-weight: 100;
            }
        </style>
        <script>
            $(document).ready(function () {
                var table = $('#table_detail').DataTable({
                    "ajax": {
                        "url": "/bifast_simulator/rpts",
                        "type": "GET",
                        "dataSrc": "",
                        "contentType": "application/json"
                    },
                    "columns": [
                        {data: "date_internal"},
                        {data: "rrn"},
                        {data: "trx_id"},
                        {data: "end_to_end_id"},
                        {data: "trx_type"},
                        {data: "from_account"},
                        {data: "to_account"},
                        {data: "amount"},
                        {data: "from_bank"},
                        {data: "to_bank"},
                        {data: "from_name"},
                        {data: "to_name"},
                        {data: "from_num_id"},
                        {data: "to_num_id"},
                        {data: "notif"},
                        {data: "status"}
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
                                            title: "Report",
                                            exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                            footer: true
                                        },
                                        {
                                            extend: "csvHtml5",
                                            title: "Report",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "excelHtml5",
                                            title: "Report",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "pdfHtml5",
                                            title: "Report",
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
                    ],

                });
                $.fn.dataTable.ext.errMode = 'none';

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
                                                        Report
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="tab-content" style="width: 100%">
                                                    <div id="detail" class="tab-pane fade in active show">
                                                        <div class="container" id="ref_tabel_detail" style="overflow: auto">        
                                                            <table class="table" id="table_detail">
                                                                <thead>
                                                                    <tr>
                                                                        <th>date Internal</th>
                                                                        <th>msg id</th>
                                                                        <th>trx id</th>
                                                                        <th>end to end id</th>
                                                                        <th>trx type</th>
                                                                        <th>debt account</th>
                                                                        <th>crdt account</th>
                                                                        <th>amount</th>
                                                                        <th>debt bank</th>
                                                                        <th>crdt bank</th>
                                                                        <th>debt name</th>
                                                                        <th>crdt name</th>
                                                                        <th>debt id</th>
                                                                        <th>crdt id</th>
                                                                        <th>notif</th>
                                                                        <th>status</th>                                                                        
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
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- Main Footer -->
            <%@include file='footer.jsp'%>
        </div>
    </body>
</html>
