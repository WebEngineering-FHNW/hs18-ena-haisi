<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Posters</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <asset:stylesheet src="bootstrap.css"/>

    <style>
    body {
        margin: 0 auto;
        width: 50vw;
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        background-color: #ecf4f1;
    }

    .ch-flag {
        width: 3em;
        height: 3em;
        background: url('data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAzMjAgMzIwIj4NCjxwYXRoIGZpbGw9IiNkNTJiMWUiIGQ9Im0wLDBoMzIwdjMyMGgtMzIweiIvPg0KPGcgZmlsbD0iI2ZmZiI+DQo8cGF0aCBkPSJtNjAsMTMwaDIwMHY2MGgtMjAweiIvPg0KPHBhdGggZD0ibTEzMCw2MGg2MHYyMDBoLTYweiIvPg0KPC9nPg0KPC9zdmc+DQo=') no-repeat left top;
    }

    nav {
        width: 100%;
        height: 3em;
        background-color: #d52b1e;
        align-content: center;
        display: flex;
    }

    nav > a {
        align-self: center;
        color: white;
        text-decoration: none;
        font-weight: bold;
    }

    .grid {
        display: grid;
        grid-template-columns: 50% 50%;
        grid-template-rows: auto;
        grid-template-areas: "poster info-grid";
    }

    .info-grid {
        display: grid;
        grid-template-columns: 50% 50%;
        grid-template-rows: 3em 1fr;
        grid-template-areas: "artist tags" "description description";
        padding-left: 1em;
    }

    .poster {
        grid-area: poster;
        width: 100%;
    }

    .artist {
        grid-area: artist;
    }

    .tags {
        grid-area: tags;
        text-align: right;
    }

    .description {
        grid-area: description;
    }

    @media only screen and (max-device-width: 480px) {

        body {
            margin: 0 auto;
            width: 100vw;
        }

        .grid {
            display: grid;
            background-color: yellow;
            grid-gap: 10px;
            grid-template-columns: 100%;
            grid-template-rows: auto;
            grid-template-areas: "poster" "info-grid";
        }
    }
    </style>
</head>

<body>

<nav>
    <div class="ch-flag"></div>
    <a href="/admin">${message(code:'grid.admin', default: 'Admin')}</a>
</nav>

<g:each in="${posterList}" var="poster">
    <div class="grid">
        <img class="poster" src="<g:createLink controller="poster" action="featuredImage" id="${poster.id}"/>"/>

        <div class="info-grid">
            <span class="artist">${poster.artist}</span>
            <span class="tags">${poster.tags}</span>
            <span class="description">
                ${poster.description}
            </span>
        </div>
    </div>

    <hr/>
</g:each>

</body>
</html>